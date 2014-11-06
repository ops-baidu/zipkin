/*
 * Copyright 2012 Twitter Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package com.twitter.zipkin.common

import com.twitter.util.Duration

/**
 * @param timestamp when was this annotation created? microseconds from epoch
 * @param value description of what happened at the timestamp could for example be "cache miss for key: x"
 * @param host host this annotation was created on
 * @param duration how long did the operation this annotation represent take?
 */
case class Annotation(timestamp: Long, value: String, host: Option[Endpoint], duration: Option[Duration] = None)
  extends Ordered[Annotation]{
  def serviceName = host.map(_.serviceName).getOrElse("Unknown service name")

  /**
   * @return diff between timestamps of the two annotations.
   */
  def -(annotation: Annotation): Long = timestamp - annotation.timestamp

  override def compare(that: Annotation): Int = {
    if (this.timestamp != that.timestamp)
      (this.timestamp - that.timestamp).toInt
    else if (this.value != that.value)
      this.value compare that.value
    else if (this.host != that.host)
      this.host.getOrElse {return -1} compare that.host.getOrElse {return 1}
    else if (this.duration != that.duration)
      this.duration.getOrElse {return -1} compare that.duration.getOrElse {return 1}
    else
      0
  }

  def plusServiceNamePrefix (prefix: String) = {
    this.copy(host = this.host.map(_.plusServiceNamePrefix(prefix)))
  }
}
