// <auto-generated>
//     Generated by the protocol buffer compiler.  DO NOT EDIT!
//     source: PBCommon.proto
// </auto-generated>
#pragma warning disable 1591, 0612, 3021
#region Designer generated code

using pb = global::Google.Protobuf;
using pbc = global::Google.Protobuf.Collections;
using pbr = global::Google.Protobuf.Reflection;
using scg = global::System.Collections.Generic;
namespace Protocol {

  /// <summary>Holder for reflection information generated from PBCommon.proto</summary>
  public static partial class PBCommonReflection {

    #region Descriptor
    /// <summary>File descriptor for PBCommon.proto</summary>
    public static pbr::FileDescriptor Descriptor {
      get { return descriptor; }
    }
    private static pbr::FileDescriptor descriptor;

    static PBCommonReflection() {
      byte[] descriptorData = global::System.Convert.FromBase64String(
          string.Concat(
            "Cg5QQkNvbW1vbi5wcm90bxIIUHJvdG9jb2wiKQoHQWNrVGlwcxIOCgZ0aXBz",
            "SWQYASABKAUSDgoGcGFyYW1zGAIgAygFQioKHmNvbS5jYXQuc2VydmVyLmdh",
            "bWUuZGF0YS5wcm90b0IIUEJDb21tb25iBnByb3RvMw=="));
      descriptor = pbr::FileDescriptor.FromGeneratedCode(descriptorData,
          new pbr::FileDescriptor[] { },
          new pbr::GeneratedClrTypeInfo(null, null, new pbr::GeneratedClrTypeInfo[] {
            new pbr::GeneratedClrTypeInfo(typeof(global::Protocol.AckTips), global::Protocol.AckTips.Parser, new[]{ "TipsId", "Params" }, null, null, null, null)
          }));
    }
    #endregion

  }
  #region Messages
  /// <summary>
  ///tips信息
  /// </summary>
  public sealed partial class AckTips : pb::IMessage<AckTips> {
    private static readonly pb::MessageParser<AckTips> _parser = new pb::MessageParser<AckTips>(() => new AckTips());
    private pb::UnknownFieldSet _unknownFields;
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public static pb::MessageParser<AckTips> Parser { get { return _parser; } }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public static pbr::MessageDescriptor Descriptor {
      get { return global::Protocol.PBCommonReflection.Descriptor.MessageTypes[0]; }
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    pbr::MessageDescriptor pb::IMessage.Descriptor {
      get { return Descriptor; }
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public AckTips() {
      OnConstruction();
    }

    partial void OnConstruction();

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public AckTips(AckTips other) : this() {
      tipsId_ = other.tipsId_;
      params_ = other.params_.Clone();
      _unknownFields = pb::UnknownFieldSet.Clone(other._unknownFields);
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public AckTips Clone() {
      return new AckTips(this);
    }

    /// <summary>Field number for the "tipsId" field.</summary>
    public const int TipsIdFieldNumber = 1;
    private int tipsId_;
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public int TipsId {
      get { return tipsId_; }
      set {
        tipsId_ = value;
      }
    }

    /// <summary>Field number for the "params" field.</summary>
    public const int ParamsFieldNumber = 2;
    private static readonly pb::FieldCodec<int> _repeated_params_codec
        = pb::FieldCodec.ForInt32(18);
    private readonly pbc::RepeatedField<int> params_ = new pbc::RepeatedField<int>();
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public pbc::RepeatedField<int> Params {
      get { return params_; }
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public override bool Equals(object other) {
      return Equals(other as AckTips);
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public bool Equals(AckTips other) {
      if (ReferenceEquals(other, null)) {
        return false;
      }
      if (ReferenceEquals(other, this)) {
        return true;
      }
      if (TipsId != other.TipsId) return false;
      if(!params_.Equals(other.params_)) return false;
      return Equals(_unknownFields, other._unknownFields);
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public override int GetHashCode() {
      int hash = 1;
      if (TipsId != 0) hash ^= TipsId.GetHashCode();
      hash ^= params_.GetHashCode();
      if (_unknownFields != null) {
        hash ^= _unknownFields.GetHashCode();
      }
      return hash;
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public override string ToString() {
      return pb::JsonFormatter.ToDiagnosticString(this);
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public void WriteTo(pb::CodedOutputStream output) {
      if (TipsId != 0) {
        output.WriteRawTag(8);
        output.WriteInt32(TipsId);
      }
      params_.WriteTo(output, _repeated_params_codec);
      if (_unknownFields != null) {
        _unknownFields.WriteTo(output);
      }
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public int CalculateSize() {
      int size = 0;
      if (TipsId != 0) {
        size += 1 + pb::CodedOutputStream.ComputeInt32Size(TipsId);
      }
      size += params_.CalculateSize(_repeated_params_codec);
      if (_unknownFields != null) {
        size += _unknownFields.CalculateSize();
      }
      return size;
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public void MergeFrom(AckTips other) {
      if (other == null) {
        return;
      }
      if (other.TipsId != 0) {
        TipsId = other.TipsId;
      }
      params_.Add(other.params_);
      _unknownFields = pb::UnknownFieldSet.MergeFrom(_unknownFields, other._unknownFields);
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public void MergeFrom(pb::CodedInputStream input) {
      uint tag;
      while ((tag = input.ReadTag()) != 0) {
        switch(tag) {
          default:
            _unknownFields = pb::UnknownFieldSet.MergeFieldFrom(_unknownFields, input);
            break;
          case 8: {
            TipsId = input.ReadInt32();
            break;
          }
          case 18:
          case 16: {
            params_.AddEntriesFrom(input, _repeated_params_codec);
            break;
          }
        }
      }
    }

  }

  #endregion

}

#endregion Designer generated code