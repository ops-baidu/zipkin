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

import java.nio.ByteBuffer

case class BinaryAnnotation(
  key: String,
  value: ByteBuffer,
  annotationType: AnnotationType,
  host: Option[Endpoint]
) {
  def plusServiceNamePrefix (prefix: String) = {
    this.copy(host = this.host.map(_.plusServiceNamePrefix(prefix)))
  }
}
