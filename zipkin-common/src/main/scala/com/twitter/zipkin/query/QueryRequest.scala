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
package com.twitter.zipkin.query

import com.twitter.zipkin.common.BinaryAnnotation

case class QueryRequest(
  serviceName: String,
  spanName: Option[String],
  annotations: Option[Seq[String]],
  binaryAnnotations: Option[Seq[BinaryAnnotation]],
  endTs: Long,
  limit: Int,
  order: Order,
  startTs: Long
) {
  def hasAnnotations = {
    (annotations.map { _.length } getOrElse 0) +
    (binaryAnnotations.map { _.length } getOrElse 0) > 0
  }
}
