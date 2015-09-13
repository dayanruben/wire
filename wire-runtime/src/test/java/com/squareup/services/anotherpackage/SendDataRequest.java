// Code generated by Wire protocol buffer compiler, do not edit.
// Source file: ../wire-runtime/src/test/proto/request_response.proto at 3:1
package com.squareup.services.anotherpackage;

import com.squareup.wire.Message;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.WireField;
import java.lang.Object;
import java.lang.Override;
import okio.ByteString;

public final class SendDataRequest extends Message<SendDataRequest> {
  public static final ProtoAdapter<SendDataRequest> ADAPTER = ProtoAdapter.forMessage(SendDataRequest.class);

  private static final long serialVersionUID = 0L;

  public static final ByteString DEFAULT_DATA = ByteString.EMPTY;

  @WireField(
      tag = 1,
      adapter = "com.squareup.wire.ProtoAdapter#BYTES"
  )
  public final ByteString data;

  public SendDataRequest(ByteString data) {
    this.data = data;
  }

  private SendDataRequest(Builder builder) {
    this(builder.data);
    setBuilder(builder);
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) return true;
    if (!(other instanceof SendDataRequest)) return false;
    return equals(data, ((SendDataRequest) other).data);
  }

  @Override
  public int hashCode() {
    int result = hashCode;
    return result != 0 ? result : (hashCode = data != null ? data.hashCode() : 0);
  }

  public static final class Builder extends com.squareup.wire.Message.Builder<SendDataRequest, Builder> {
    public ByteString data;

    public Builder() {
    }

    public Builder(SendDataRequest message) {
      super(message);
      if (message == null) return;
      this.data = message.data;
    }

    public Builder data(ByteString data) {
      this.data = data;
      return this;
    }

    @Override
    public SendDataRequest build() {
      return new SendDataRequest(this);
    }
  }
}
