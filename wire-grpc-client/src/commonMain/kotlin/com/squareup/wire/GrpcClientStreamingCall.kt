/*
 * Copyright (C) 2025 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.squareup.wire

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.SendChannel
import okio.IOException
import okio.Timeout

/**
 * A single streaming call to a remote server.
 *
 *  * This class handles a client streaming call . The send channel or message sink accept zero or more
 *    messages. The returned deferred repsonse will ensure that the corresponding send channel or message sink
 *    is closed before returning the single response message.
 *
 * A gRPC call cannot be executed twice.
 *
 * gRPC calls can be [suspending][executeIn] or [blocking][executeBlocking]. Use whichever mechanism
 * works at your call site: the bytes transmitted on the network are the same.
 */
interface GrpcClientStreamingCall<S : Any, R : Any> {
  /** The method invoked by this call. */
  val method: GrpcMethod<S, R>

  /**
   * Configures how long the call can take to complete before it is automatically canceled. The
   * timeout applies to the full set of messages transmitted. For long-running streams you must
   * configure a sufficiently long timeout.
   */
  val timeout: Timeout

  /**
   * A map containing request metadata. This is initially empty; it can be assigned to any other map
   * of metadata before the call is executed. It is an error to set this value after the call is
   * executed.
   */
  var requestMetadata: Map<String, String>

  /**
   * A map containing response metadata. This is null until the call has executed, at which point
   * it will be non-null if the call completed successfully. It may also be non-null in failure
   * cases if the failure was not a problem of connectivity. For example, if the gRPC call fails
   * with an HTTP 503 error, response metadata will be present.
   */
  val responseMetadata: Map<String, String>?

  /**
   * Attempts to cancel the call. This function is safe to call concurrently with execution. When
   * canceled, execution fails with an immediate [IOException] rather than waiting to complete
   * normally.
   */
  fun cancel()

  /** True if [cancel] was called. */
  fun isCanceled(): Boolean

  /**
   * Enqueues this call for execution and returns a channel to send the call's messages and a deferred
   * response that will ensure the sender is closed and return the single response.
   * This uses the [Dispatchers.IO] to transmit outbound messages.
   */
  fun executeIn(scope: CoroutineScope): Pair<SendChannel<S>, Deferred<R>>

  /**
   * Enqueues this call for execution and returns a stream to send call's messages and a deferred
   * response that will ensure the sender is closed and return the single response.
   * Reads and writes on the returned streams are blocking.
   */
  fun executeBlocking(): Pair<MessageSink<S>, GrpcDeferredResponse<R>>

  /**
   * Returns true if [executeIn] or [executeBlocking] was called. It is an error to execute a call
   * more than once.
   */
  fun isExecuted(): Boolean

  /**
   * Create a new, identical gRPC call to this one which can be enqueued or executed even if this
   * call has already been.
   */
  fun clone(): GrpcClientStreamingCall<S, R>
}
