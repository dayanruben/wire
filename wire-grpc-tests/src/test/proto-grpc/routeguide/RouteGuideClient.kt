// Code generated by Wire protocol buffer compiler, do not edit.
// Source: routeguide.RouteGuide in routeguide/RouteGuideProto.proto
@file:Suppress(
  "DEPRECATION",
  "RUNTIME_ANNOTATION_NOT_SUPPORTED",
)

package routeguide

import com.squareup.wire.GrpcCall
import com.squareup.wire.GrpcClientStreamingCall
import com.squareup.wire.GrpcServerStreamingCall
import com.squareup.wire.GrpcStreamingCall
import com.squareup.wire.Service
import kotlin.Suppress

/**
 * Interface exported by the server.
 */
public interface RouteGuideClient : Service {
  /**
   * A simple RPC.
   *
   * Obtains the feature at a given position.
   *
   * A feature with an empty name is returned if there's no feature at the given
   * position.
   */
  public fun GetFeature(): GrpcCall<Point, Feature>

  /**
   * A server-to-client streaming RPC.
   *
   * Obtains the Features available within the given Rectangle.  Results are
   * streamed rather than returned at once (e.g. in a response message with a
   * repeated field), as the rectangle may cover a large area and contain a
   * huge number of features.
   */
  public fun ListFeatures(): GrpcServerStreamingCall<Rectangle, Feature>

  /**
   * A client-to-server streaming RPC.
   *
   * Accepts a stream of Points on a route being traversed, returning a
   * RouteSummary when traversal is completed.
   */
  public fun RecordRoute(): GrpcClientStreamingCall<Point, RouteSummary>

  /**
   * A Bidirectional streaming RPC.
   *
   * Accepts a stream of RouteNotes sent while a route is being traversed,
   * while receiving other RouteNotes (e.g. from other users).
   */
  public fun RouteChat(): GrpcStreamingCall<RouteNote, RouteNote>
}
