// <auto-generated>
//     Generated by the protocol buffer compiler.  DO NOT EDIT!
//     source: plaid.proto
// </auto-generated>
#pragma warning disable 1591, 0612, 3021
#region Designer generated code

using pb = global::Google.Protobuf;
using pbc = global::Google.Protobuf.Collections;
using pbr = global::Google.Protobuf.Reflection;
using scg = global::System.Collections.Generic;
namespace Com.Proto.PlaidMessage {

  /// <summary>Holder for reflection information generated from plaid.proto</summary>
  public static partial class PlaidReflection {

    #region Descriptor
    /// <summary>File descriptor for plaid.proto</summary>
    public static pbr::FileDescriptor Descriptor {
      get { return descriptor; }
    }
    private static pbr::FileDescriptor descriptor;

    static PlaidReflection() {
      byte[] descriptorData = global::System.Convert.FromBase64String(
          string.Concat(
            "CgtwbGFpZC5wcm90bxIWY29tLnByb3RvLnBsYWlkTWVzc2FnZSIsCg1Ta2ls",
            "bER0b1Byb3RvEg8KB3NraWxsSWQYASABKAUSCgoCbHYYAiABKAUiKAoRUmVx",
            "U2tpbGxJbmZvUHJvdG8SEwoLY29uZmlkYW50SWQYASABKAUiOQoRUmVxVXBT",
            "a2lsbEx2UHJvdG8SEwoLY29uZmlkYW50SWQYASABKAUSDwoHc2tpbGxJZBgC",
            "IAEoBSJNChFSZXNTa2lsbEluZm9Qcm90bxI4Cglza2lsbExpc3QYASADKAsy",
            "JS5jb20ucHJvdG8ucGxhaWRNZXNzYWdlLlNraWxsRHRvUHJvdG8iSQoRUmVz",
            "VXBTa2lsbEx2UHJvdG8SNAoFc2tpbGwYASABKAsyJS5jb20ucHJvdG8ucGxh",
            "aWRNZXNzYWdlLlNraWxsRHRvUHJvdG8iOgoSUmVzVW5sb2NrU2tsbFByb3Rv",
            "EhMKC2NvbmZpZGFudElkGAEgASgFEg8KB3NraWxsSWQYAiABKAVCLQoeY29t",
            "LmdhbWUubW9kdWxlLnBsYWlkLnByb3RvY29sQgtQbGFpZFByb3Rvc2IGcHJv",
            "dG8z"));
      descriptor = pbr::FileDescriptor.FromGeneratedCode(descriptorData,
          new pbr::FileDescriptor[] { },
          new pbr::GeneratedClrTypeInfo(null, null, new pbr::GeneratedClrTypeInfo[] {
            new pbr::GeneratedClrTypeInfo(typeof(global::Com.Proto.PlaidMessage.SkillDtoProto), global::Com.Proto.PlaidMessage.SkillDtoProto.Parser, new[]{ "SkillId", "Lv" }, null, null, null, null),
            new pbr::GeneratedClrTypeInfo(typeof(global::Com.Proto.PlaidMessage.ReqSkillInfoProto), global::Com.Proto.PlaidMessage.ReqSkillInfoProto.Parser, new[]{ "ConfidantId" }, null, null, null, null),
            new pbr::GeneratedClrTypeInfo(typeof(global::Com.Proto.PlaidMessage.ReqUpSkillLvProto), global::Com.Proto.PlaidMessage.ReqUpSkillLvProto.Parser, new[]{ "ConfidantId", "SkillId" }, null, null, null, null),
            new pbr::GeneratedClrTypeInfo(typeof(global::Com.Proto.PlaidMessage.ResSkillInfoProto), global::Com.Proto.PlaidMessage.ResSkillInfoProto.Parser, new[]{ "SkillList" }, null, null, null, null),
            new pbr::GeneratedClrTypeInfo(typeof(global::Com.Proto.PlaidMessage.ResUpSkillLvProto), global::Com.Proto.PlaidMessage.ResUpSkillLvProto.Parser, new[]{ "Skill" }, null, null, null, null),
            new pbr::GeneratedClrTypeInfo(typeof(global::Com.Proto.PlaidMessage.ResUnlockSkllProto), global::Com.Proto.PlaidMessage.ResUnlockSkllProto.Parser, new[]{ "ConfidantId", "SkillId" }, null, null, null, null)
          }));
    }
    #endregion

  }
  #region Messages
  /// <summary>
  /// 协议列表
  /// 技能		msgId=161001
  /// </summary>
  public sealed partial class SkillDtoProto : pb::IMessage<SkillDtoProto> {
    private static readonly pb::MessageParser<SkillDtoProto> _parser = new pb::MessageParser<SkillDtoProto>(() => new SkillDtoProto());
    private pb::UnknownFieldSet _unknownFields;
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public static pb::MessageParser<SkillDtoProto> Parser { get { return _parser; } }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public static pbr::MessageDescriptor Descriptor {
      get { return global::Com.Proto.PlaidMessage.PlaidReflection.Descriptor.MessageTypes[0]; }
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    pbr::MessageDescriptor pb::IMessage.Descriptor {
      get { return Descriptor; }
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public SkillDtoProto() {
      OnConstruction();
    }

    partial void OnConstruction();

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public SkillDtoProto(SkillDtoProto other) : this() {
      skillId_ = other.skillId_;
      lv_ = other.lv_;
      _unknownFields = pb::UnknownFieldSet.Clone(other._unknownFields);
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public SkillDtoProto Clone() {
      return new SkillDtoProto(this);
    }

    /// <summary>Field number for the "skillId" field.</summary>
    public const int SkillIdFieldNumber = 1;
    private int skillId_;
    /// <summary>
    ///技能 id
    /// </summary>
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public int SkillId {
      get { return skillId_; }
      set {
        skillId_ = value;
      }
    }

    /// <summary>Field number for the "lv" field.</summary>
    public const int LvFieldNumber = 2;
    private int lv_;
    /// <summary>
    ///技能等级
    /// </summary>
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public int Lv {
      get { return lv_; }
      set {
        lv_ = value;
      }
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public override bool Equals(object other) {
      return Equals(other as SkillDtoProto);
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public bool Equals(SkillDtoProto other) {
      if (ReferenceEquals(other, null)) {
        return false;
      }
      if (ReferenceEquals(other, this)) {
        return true;
      }
      if (SkillId != other.SkillId) return false;
      if (Lv != other.Lv) return false;
      return Equals(_unknownFields, other._unknownFields);
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public override int GetHashCode() {
      int hash = 1;
      if (SkillId != 0) hash ^= SkillId.GetHashCode();
      if (Lv != 0) hash ^= Lv.GetHashCode();
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
      if (SkillId != 0) {
        output.WriteRawTag(8);
        output.WriteInt32(SkillId);
      }
      if (Lv != 0) {
        output.WriteRawTag(16);
        output.WriteInt32(Lv);
      }
      if (_unknownFields != null) {
        _unknownFields.WriteTo(output);
      }
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public int CalculateSize() {
      int size = 0;
      if (SkillId != 0) {
        size += 1 + pb::CodedOutputStream.ComputeInt32Size(SkillId);
      }
      if (Lv != 0) {
        size += 1 + pb::CodedOutputStream.ComputeInt32Size(Lv);
      }
      if (_unknownFields != null) {
        size += _unknownFields.CalculateSize();
      }
      return size;
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public void MergeFrom(SkillDtoProto other) {
      if (other == null) {
        return;
      }
      if (other.SkillId != 0) {
        SkillId = other.SkillId;
      }
      if (other.Lv != 0) {
        Lv = other.Lv;
      }
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
            SkillId = input.ReadInt32();
            break;
          }
          case 16: {
            Lv = input.ReadInt32();
            break;
          }
        }
      }
    }

  }

  /// <summary>
  /// 请求技能信息		msgId=161101
  /// </summary>
  public sealed partial class ReqSkillInfoProto : pb::IMessage<ReqSkillInfoProto> {
    private static readonly pb::MessageParser<ReqSkillInfoProto> _parser = new pb::MessageParser<ReqSkillInfoProto>(() => new ReqSkillInfoProto());
    private pb::UnknownFieldSet _unknownFields;
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public static pb::MessageParser<ReqSkillInfoProto> Parser { get { return _parser; } }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public static pbr::MessageDescriptor Descriptor {
      get { return global::Com.Proto.PlaidMessage.PlaidReflection.Descriptor.MessageTypes[1]; }
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    pbr::MessageDescriptor pb::IMessage.Descriptor {
      get { return Descriptor; }
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public ReqSkillInfoProto() {
      OnConstruction();
    }

    partial void OnConstruction();

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public ReqSkillInfoProto(ReqSkillInfoProto other) : this() {
      confidantId_ = other.confidantId_;
      _unknownFields = pb::UnknownFieldSet.Clone(other._unknownFields);
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public ReqSkillInfoProto Clone() {
      return new ReqSkillInfoProto(this);
    }

    /// <summary>Field number for the "confidantId" field.</summary>
    public const int ConfidantIdFieldNumber = 1;
    private int confidantId_;
    /// <summary>
    ///知己 id
    /// </summary>
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public int ConfidantId {
      get { return confidantId_; }
      set {
        confidantId_ = value;
      }
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public override bool Equals(object other) {
      return Equals(other as ReqSkillInfoProto);
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public bool Equals(ReqSkillInfoProto other) {
      if (ReferenceEquals(other, null)) {
        return false;
      }
      if (ReferenceEquals(other, this)) {
        return true;
      }
      if (ConfidantId != other.ConfidantId) return false;
      return Equals(_unknownFields, other._unknownFields);
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public override int GetHashCode() {
      int hash = 1;
      if (ConfidantId != 0) hash ^= ConfidantId.GetHashCode();
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
      if (ConfidantId != 0) {
        output.WriteRawTag(8);
        output.WriteInt32(ConfidantId);
      }
      if (_unknownFields != null) {
        _unknownFields.WriteTo(output);
      }
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public int CalculateSize() {
      int size = 0;
      if (ConfidantId != 0) {
        size += 1 + pb::CodedOutputStream.ComputeInt32Size(ConfidantId);
      }
      if (_unknownFields != null) {
        size += _unknownFields.CalculateSize();
      }
      return size;
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public void MergeFrom(ReqSkillInfoProto other) {
      if (other == null) {
        return;
      }
      if (other.ConfidantId != 0) {
        ConfidantId = other.ConfidantId;
      }
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
            ConfidantId = input.ReadInt32();
            break;
          }
        }
      }
    }

  }

  /// <summary>
  /// 请求提升技能等级		msgId=161102
  /// </summary>
  public sealed partial class ReqUpSkillLvProto : pb::IMessage<ReqUpSkillLvProto> {
    private static readonly pb::MessageParser<ReqUpSkillLvProto> _parser = new pb::MessageParser<ReqUpSkillLvProto>(() => new ReqUpSkillLvProto());
    private pb::UnknownFieldSet _unknownFields;
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public static pb::MessageParser<ReqUpSkillLvProto> Parser { get { return _parser; } }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public static pbr::MessageDescriptor Descriptor {
      get { return global::Com.Proto.PlaidMessage.PlaidReflection.Descriptor.MessageTypes[2]; }
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    pbr::MessageDescriptor pb::IMessage.Descriptor {
      get { return Descriptor; }
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public ReqUpSkillLvProto() {
      OnConstruction();
    }

    partial void OnConstruction();

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public ReqUpSkillLvProto(ReqUpSkillLvProto other) : this() {
      confidantId_ = other.confidantId_;
      skillId_ = other.skillId_;
      _unknownFields = pb::UnknownFieldSet.Clone(other._unknownFields);
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public ReqUpSkillLvProto Clone() {
      return new ReqUpSkillLvProto(this);
    }

    /// <summary>Field number for the "confidantId" field.</summary>
    public const int ConfidantIdFieldNumber = 1;
    private int confidantId_;
    /// <summary>
    ///知己 id
    /// </summary>
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public int ConfidantId {
      get { return confidantId_; }
      set {
        confidantId_ = value;
      }
    }

    /// <summary>Field number for the "skillId" field.</summary>
    public const int SkillIdFieldNumber = 2;
    private int skillId_;
    /// <summary>
    ///技能 id
    /// </summary>
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public int SkillId {
      get { return skillId_; }
      set {
        skillId_ = value;
      }
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public override bool Equals(object other) {
      return Equals(other as ReqUpSkillLvProto);
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public bool Equals(ReqUpSkillLvProto other) {
      if (ReferenceEquals(other, null)) {
        return false;
      }
      if (ReferenceEquals(other, this)) {
        return true;
      }
      if (ConfidantId != other.ConfidantId) return false;
      if (SkillId != other.SkillId) return false;
      return Equals(_unknownFields, other._unknownFields);
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public override int GetHashCode() {
      int hash = 1;
      if (ConfidantId != 0) hash ^= ConfidantId.GetHashCode();
      if (SkillId != 0) hash ^= SkillId.GetHashCode();
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
      if (ConfidantId != 0) {
        output.WriteRawTag(8);
        output.WriteInt32(ConfidantId);
      }
      if (SkillId != 0) {
        output.WriteRawTag(16);
        output.WriteInt32(SkillId);
      }
      if (_unknownFields != null) {
        _unknownFields.WriteTo(output);
      }
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public int CalculateSize() {
      int size = 0;
      if (ConfidantId != 0) {
        size += 1 + pb::CodedOutputStream.ComputeInt32Size(ConfidantId);
      }
      if (SkillId != 0) {
        size += 1 + pb::CodedOutputStream.ComputeInt32Size(SkillId);
      }
      if (_unknownFields != null) {
        size += _unknownFields.CalculateSize();
      }
      return size;
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public void MergeFrom(ReqUpSkillLvProto other) {
      if (other == null) {
        return;
      }
      if (other.ConfidantId != 0) {
        ConfidantId = other.ConfidantId;
      }
      if (other.SkillId != 0) {
        SkillId = other.SkillId;
      }
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
            ConfidantId = input.ReadInt32();
            break;
          }
          case 16: {
            SkillId = input.ReadInt32();
            break;
          }
        }
      }
    }

  }

  /// <summary>
  /// 返回技能信息		msgId=161201
  /// </summary>
  public sealed partial class ResSkillInfoProto : pb::IMessage<ResSkillInfoProto> {
    private static readonly pb::MessageParser<ResSkillInfoProto> _parser = new pb::MessageParser<ResSkillInfoProto>(() => new ResSkillInfoProto());
    private pb::UnknownFieldSet _unknownFields;
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public static pb::MessageParser<ResSkillInfoProto> Parser { get { return _parser; } }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public static pbr::MessageDescriptor Descriptor {
      get { return global::Com.Proto.PlaidMessage.PlaidReflection.Descriptor.MessageTypes[3]; }
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    pbr::MessageDescriptor pb::IMessage.Descriptor {
      get { return Descriptor; }
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public ResSkillInfoProto() {
      OnConstruction();
    }

    partial void OnConstruction();

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public ResSkillInfoProto(ResSkillInfoProto other) : this() {
      skillList_ = other.skillList_.Clone();
      _unknownFields = pb::UnknownFieldSet.Clone(other._unknownFields);
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public ResSkillInfoProto Clone() {
      return new ResSkillInfoProto(this);
    }

    /// <summary>Field number for the "skillList" field.</summary>
    public const int SkillListFieldNumber = 1;
    private static readonly pb::FieldCodec<global::Com.Proto.PlaidMessage.SkillDtoProto> _repeated_skillList_codec
        = pb::FieldCodec.ForMessage(10, global::Com.Proto.PlaidMessage.SkillDtoProto.Parser);
    private readonly pbc::RepeatedField<global::Com.Proto.PlaidMessage.SkillDtoProto> skillList_ = new pbc::RepeatedField<global::Com.Proto.PlaidMessage.SkillDtoProto>();
    /// <summary>
    ///技能列表
    /// </summary>
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public pbc::RepeatedField<global::Com.Proto.PlaidMessage.SkillDtoProto> SkillList {
      get { return skillList_; }
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public override bool Equals(object other) {
      return Equals(other as ResSkillInfoProto);
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public bool Equals(ResSkillInfoProto other) {
      if (ReferenceEquals(other, null)) {
        return false;
      }
      if (ReferenceEquals(other, this)) {
        return true;
      }
      if(!skillList_.Equals(other.skillList_)) return false;
      return Equals(_unknownFields, other._unknownFields);
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public override int GetHashCode() {
      int hash = 1;
      hash ^= skillList_.GetHashCode();
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
      skillList_.WriteTo(output, _repeated_skillList_codec);
      if (_unknownFields != null) {
        _unknownFields.WriteTo(output);
      }
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public int CalculateSize() {
      int size = 0;
      size += skillList_.CalculateSize(_repeated_skillList_codec);
      if (_unknownFields != null) {
        size += _unknownFields.CalculateSize();
      }
      return size;
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public void MergeFrom(ResSkillInfoProto other) {
      if (other == null) {
        return;
      }
      skillList_.Add(other.skillList_);
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
          case 10: {
            skillList_.AddEntriesFrom(input, _repeated_skillList_codec);
            break;
          }
        }
      }
    }

  }

  /// <summary>
  /// 返回技能信息		msgId=161202
  /// </summary>
  public sealed partial class ResUpSkillLvProto : pb::IMessage<ResUpSkillLvProto> {
    private static readonly pb::MessageParser<ResUpSkillLvProto> _parser = new pb::MessageParser<ResUpSkillLvProto>(() => new ResUpSkillLvProto());
    private pb::UnknownFieldSet _unknownFields;
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public static pb::MessageParser<ResUpSkillLvProto> Parser { get { return _parser; } }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public static pbr::MessageDescriptor Descriptor {
      get { return global::Com.Proto.PlaidMessage.PlaidReflection.Descriptor.MessageTypes[4]; }
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    pbr::MessageDescriptor pb::IMessage.Descriptor {
      get { return Descriptor; }
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public ResUpSkillLvProto() {
      OnConstruction();
    }

    partial void OnConstruction();

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public ResUpSkillLvProto(ResUpSkillLvProto other) : this() {
      skill_ = other.skill_ != null ? other.skill_.Clone() : null;
      _unknownFields = pb::UnknownFieldSet.Clone(other._unknownFields);
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public ResUpSkillLvProto Clone() {
      return new ResUpSkillLvProto(this);
    }

    /// <summary>Field number for the "skill" field.</summary>
    public const int SkillFieldNumber = 1;
    private global::Com.Proto.PlaidMessage.SkillDtoProto skill_;
    /// <summary>
    ///升级之后的技能信息
    /// </summary>
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public global::Com.Proto.PlaidMessage.SkillDtoProto Skill {
      get { return skill_; }
      set {
        skill_ = value;
      }
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public override bool Equals(object other) {
      return Equals(other as ResUpSkillLvProto);
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public bool Equals(ResUpSkillLvProto other) {
      if (ReferenceEquals(other, null)) {
        return false;
      }
      if (ReferenceEquals(other, this)) {
        return true;
      }
      if (!object.Equals(Skill, other.Skill)) return false;
      return Equals(_unknownFields, other._unknownFields);
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public override int GetHashCode() {
      int hash = 1;
      if (skill_ != null) hash ^= Skill.GetHashCode();
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
      if (skill_ != null) {
        output.WriteRawTag(10);
        output.WriteMessage(Skill);
      }
      if (_unknownFields != null) {
        _unknownFields.WriteTo(output);
      }
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public int CalculateSize() {
      int size = 0;
      if (skill_ != null) {
        size += 1 + pb::CodedOutputStream.ComputeMessageSize(Skill);
      }
      if (_unknownFields != null) {
        size += _unknownFields.CalculateSize();
      }
      return size;
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public void MergeFrom(ResUpSkillLvProto other) {
      if (other == null) {
        return;
      }
      if (other.skill_ != null) {
        if (skill_ == null) {
          Skill = new global::Com.Proto.PlaidMessage.SkillDtoProto();
        }
        Skill.MergeFrom(other.Skill);
      }
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
          case 10: {
            if (skill_ == null) {
              Skill = new global::Com.Proto.PlaidMessage.SkillDtoProto();
            }
            input.ReadMessage(Skill);
            break;
          }
        }
      }
    }

  }

  /// <summary>
  /// 解锁技能信息推送		msgId=161203
  /// </summary>
  public sealed partial class ResUnlockSkllProto : pb::IMessage<ResUnlockSkllProto> {
    private static readonly pb::MessageParser<ResUnlockSkllProto> _parser = new pb::MessageParser<ResUnlockSkllProto>(() => new ResUnlockSkllProto());
    private pb::UnknownFieldSet _unknownFields;
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public static pb::MessageParser<ResUnlockSkllProto> Parser { get { return _parser; } }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public static pbr::MessageDescriptor Descriptor {
      get { return global::Com.Proto.PlaidMessage.PlaidReflection.Descriptor.MessageTypes[5]; }
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    pbr::MessageDescriptor pb::IMessage.Descriptor {
      get { return Descriptor; }
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public ResUnlockSkllProto() {
      OnConstruction();
    }

    partial void OnConstruction();

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public ResUnlockSkllProto(ResUnlockSkllProto other) : this() {
      confidantId_ = other.confidantId_;
      skillId_ = other.skillId_;
      _unknownFields = pb::UnknownFieldSet.Clone(other._unknownFields);
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public ResUnlockSkllProto Clone() {
      return new ResUnlockSkllProto(this);
    }

    /// <summary>Field number for the "confidantId" field.</summary>
    public const int ConfidantIdFieldNumber = 1;
    private int confidantId_;
    /// <summary>
    ///知己 id
    /// </summary>
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public int ConfidantId {
      get { return confidantId_; }
      set {
        confidantId_ = value;
      }
    }

    /// <summary>Field number for the "skillId" field.</summary>
    public const int SkillIdFieldNumber = 2;
    private int skillId_;
    /// <summary>
    ///技能 id
    /// </summary>
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public int SkillId {
      get { return skillId_; }
      set {
        skillId_ = value;
      }
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public override bool Equals(object other) {
      return Equals(other as ResUnlockSkllProto);
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public bool Equals(ResUnlockSkllProto other) {
      if (ReferenceEquals(other, null)) {
        return false;
      }
      if (ReferenceEquals(other, this)) {
        return true;
      }
      if (ConfidantId != other.ConfidantId) return false;
      if (SkillId != other.SkillId) return false;
      return Equals(_unknownFields, other._unknownFields);
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public override int GetHashCode() {
      int hash = 1;
      if (ConfidantId != 0) hash ^= ConfidantId.GetHashCode();
      if (SkillId != 0) hash ^= SkillId.GetHashCode();
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
      if (ConfidantId != 0) {
        output.WriteRawTag(8);
        output.WriteInt32(ConfidantId);
      }
      if (SkillId != 0) {
        output.WriteRawTag(16);
        output.WriteInt32(SkillId);
      }
      if (_unknownFields != null) {
        _unknownFields.WriteTo(output);
      }
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public int CalculateSize() {
      int size = 0;
      if (ConfidantId != 0) {
        size += 1 + pb::CodedOutputStream.ComputeInt32Size(ConfidantId);
      }
      if (SkillId != 0) {
        size += 1 + pb::CodedOutputStream.ComputeInt32Size(SkillId);
      }
      if (_unknownFields != null) {
        size += _unknownFields.CalculateSize();
      }
      return size;
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public void MergeFrom(ResUnlockSkllProto other) {
      if (other == null) {
        return;
      }
      if (other.ConfidantId != 0) {
        ConfidantId = other.ConfidantId;
      }
      if (other.SkillId != 0) {
        SkillId = other.SkillId;
      }
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
            ConfidantId = input.ReadInt32();
            break;
          }
          case 16: {
            SkillId = input.ReadInt32();
            break;
          }
        }
      }
    }

  }

  #endregion

}

#endregion Designer generated code