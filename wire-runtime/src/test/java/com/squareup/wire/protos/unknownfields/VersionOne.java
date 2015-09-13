// Code generated by Wire protocol buffer compiler, do not edit.
// Source file: ../wire-runtime/src/test/proto/unknown_fields.proto at 20:1
package com.squareup.wire.protos.unknownfields;

import com.squareup.wire.Message;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.WireField;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;

public final class VersionOne extends Message<VersionOne> {
  public static final ProtoAdapter<VersionOne> ADAPTER = ProtoAdapter.forMessage(VersionOne.class);

  private static final long serialVersionUID = 0L;

  public static final Integer DEFAULT_I = 0;

  @WireField(
      tag = 1,
      adapter = "com.squareup.wire.ProtoAdapter#INT32"
  )
  public final Integer i;

  public VersionOne(Integer i) {
    this.i = i;
  }

  private VersionOne(Builder builder) {
    this(builder.i);
    setBuilder(builder);
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) return true;
    if (!(other instanceof VersionOne)) return false;
    return equals(i, ((VersionOne) other).i);
  }

  @Override
  public int hashCode() {
    int result = hashCode;
    return result != 0 ? result : (hashCode = i != null ? i.hashCode() : 0);
  }

  public static final class Builder extends com.squareup.wire.Message.Builder<VersionOne, Builder> {
    public Integer i;

    public Builder() {
    }

    public Builder(VersionOne message) {
      super(message);
      if (message == null) return;
      this.i = message.i;
    }

    public Builder i(Integer i) {
      this.i = i;
      return this;
    }

    @Override
    public VersionOne build() {
      return new VersionOne(this);
    }
  }
}
