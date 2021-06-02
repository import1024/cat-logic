// <auto-generated>
//     Generated by the protocol buffer compiler.  DO NOT EDIT!
//     source: rank.proto
// </auto-generated>
#pragma warning disable 1591, 0612, 3021
#region Designer generated code

using pb = global::Google.Protobuf;
using pbc = global::Google.Protobuf.Collections;
using pbr = global::Google.Protobuf.Reflection;
using scg = global::System.Collections.Generic;
namespace Com.Proto.RankMessage {

  /// <summary>Holder for reflection information generated from rank.proto</summary>
  public static partial class RankReflection {

    #region Descriptor
    /// <summary>File descriptor for rank.proto</summary>
    public static pbr::FileDescriptor Descriptor {
      get { return descriptor; }
    }
    private static pbr::FileDescriptor descriptor;

    static RankReflection() {
      byte[] descriptorData = global::System.Convert.FromBase64String(
          string.Concat(
            "CgpyYW5rLnByb3RvEhVjb20ucHJvdG8ucmFua01lc3NhZ2UingEKDFJhbmtE",
            "dG9Qcm90bxIQCgh1bmlxdWVJZBgBIAEoAxINCgVwYXJhbRgCIAEoBRIMCgRu",
            "YW1lGAMgASgJEgoKAmx2GAQgASgFEg0KBXZhbHVlGAUgASgDEg4KBmljb25J",
            "ZBgGIAEoBRIPCgdmcmFtZUlkGAcgASgFEg8KB3RpdGxlSWQYCCABKAUSEgoK",
            "ZXh0cmFQYXJhbRgJIAEoBSI1ChBSZXFSYW5rSW5mb1Byb3RvEhAKCHJhbmtU",
            "eXBlGAEgASgFEg8KB3N1YlR5cGUYAiABKAUinwEKEFJlc1JhbmtJbmZvUHJv",
            "dG8SEAoIcmFua1R5cGUYASABKAUSDwoHc3ViVHlwZRgCIAEoBRI1CghyYW5r",
            "TGlzdBgDIAMoCzIjLmNvbS5wcm90by5yYW5rTWVzc2FnZS5SYW5rRHRvUHJv",
            "dG8SMQoEc2VsZhgEIAEoCzIjLmNvbS5wcm90by5yYW5rTWVzc2FnZS5SYW5r",
            "RHRvUHJvdG9CKwodY29tLmdhbWUubW9kdWxlLnJhbmsucHJvdG9jb2xCClJh",
            "bmtQcm90b3NiBnByb3RvMw=="));
      descriptor = pbr::FileDescriptor.FromGeneratedCode(descriptorData,
          new pbr::FileDescriptor[] { },
          new pbr::GeneratedClrTypeInfo(null, null, new pbr::GeneratedClrTypeInfo[] {
            new pbr::GeneratedClrTypeInfo(typeof(global::Com.Proto.RankMessage.RankDtoProto), global::Com.Proto.RankMessage.RankDtoProto.Parser, new[]{ "UniqueId", "Param", "Name", "Lv", "Value", "IconId", "FrameId", "TitleId", "ExtraParam" }, null, null, null, null),
            new pbr::GeneratedClrTypeInfo(typeof(global::Com.Proto.RankMessage.ReqRankInfoProto), global::Com.Proto.RankMessage.ReqRankInfoProto.Parser, new[]{ "RankType", "SubType" }, null, null, null, null),
            new pbr::GeneratedClrTypeInfo(typeof(global::Com.Proto.RankMessage.ResRankInfoProto), global::Com.Proto.RankMessage.ResRankInfoProto.Parser, new[]{ "RankType", "SubType", "RankList", "Self" }, null, null, null, null)
          }));
    }
    #endregion

  }
  #region Messages
  /// <summary>
  /// 协议列表
  /// 排行榜数据		msgId=142001
  /// </summary>
  public sealed partial class RankDtoProto : pb::IMessage<RankDtoProto> {
    private static readonly pb::MessageParser<RankDtoProto> _parser = new pb::MessageParser<RankDtoProto>(() => new RankDtoProto());
    private pb::UnknownFieldSet _unknownFields;
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public static pb::MessageParser<RankDtoProto> Parser { get { return _parser; } }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public static pbr::MessageDescriptor Descriptor {
      get { return global::Com.Proto.RankMessage.RankReflection.Descriptor.MessageTypes[0]; }
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    pbr::MessageDescriptor pb::IMessage.Descriptor {
      get { return Descriptor; }
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public RankDtoProto() {
      OnConstruction();
    }

    partial void OnConstruction();

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public RankDtoProto(RankDtoProto other) : this() {
      uniqueId_ = other.uniqueId_;
      param_ = other.param_;
      name_ = other.name_;
      lv_ = other.lv_;
      value_ = other.value_;
      iconId_ = other.iconId_;
      frameId_ = other.frameId_;
      titleId_ = other.titleId_;
      extraParam_ = other.extraParam_;
      _unknownFields = pb::UnknownFieldSet.Clone(other._unknownFields);
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public RankDtoProto Clone() {
      return new RankDtoProto(this);
    }

    /// <summary>Field number for the "uniqueId" field.</summary>
    public const int UniqueIdFieldNumber = 1;
    private long uniqueId_;
    /// <summary>
    ///唯一id，角色或联盟或其他
    /// </summary>
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public long UniqueId {
      get { return uniqueId_; }
      set {
        uniqueId_ = value;
      }
    }

    /// <summary>Field number for the "param" field.</summary>
    public const int ParamFieldNumber = 2;
    private int param_;
    /// <summary>
    ///同id可重复上榜的情况使用
    /// </summary>
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public int Param {
      get { return param_; }
      set {
        param_ = value;
      }
    }

    /// <summary>Field number for the "name" field.</summary>
    public const int NameFieldNumber = 3;
    private string name_ = "";
    /// <summary>
    ///角色名或联盟名
    /// </summary>
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public string Name {
      get { return name_; }
      set {
        name_ = pb::ProtoPreconditions.CheckNotNull(value, "value");
      }
    }

    /// <summary>Field number for the "lv" field.</summary>
    public const int LvFieldNumber = 4;
    private int lv_;
    /// <summary>
    ///等级
    /// </summary>
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public int Lv {
      get { return lv_; }
      set {
        lv_ = value;
      }
    }

    /// <summary>Field number for the "value" field.</summary>
    public const int ValueFieldNumber = 5;
    private long value_;
    /// <summary>
    ///排行榜值
    /// </summary>
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public long Value {
      get { return value_; }
      set {
        value_ = value;
      }
    }

    /// <summary>Field number for the "iconId" field.</summary>
    public const int IconIdFieldNumber = 6;
    private int iconId_;
    /// <summary>
    ///图标(头像)id
    /// </summary>
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public int IconId {
      get { return iconId_; }
      set {
        iconId_ = value;
      }
    }

    /// <summary>Field number for the "frameId" field.</summary>
    public const int FrameIdFieldNumber = 7;
    private int frameId_;
    /// <summary>
    ///头像框id
    /// </summary>
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public int FrameId {
      get { return frameId_; }
      set {
        frameId_ = value;
      }
    }

    /// <summary>Field number for the "titleId" field.</summary>
    public const int TitleIdFieldNumber = 8;
    private int titleId_;
    /// <summary>
    ///称号id
    /// </summary>
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public int TitleId {
      get { return titleId_; }
      set {
        titleId_ = value;
      }
    }

    /// <summary>Field number for the "extraParam" field.</summary>
    public const int ExtraParamFieldNumber = 9;
    private int extraParam_;
    /// <summary>
    ///额外参数,用于显示的其他参数
    /// </summary>
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public int ExtraParam {
      get { return extraParam_; }
      set {
        extraParam_ = value;
      }
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public override bool Equals(object other) {
      return Equals(other as RankDtoProto);
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public bool Equals(RankDtoProto other) {
      if (ReferenceEquals(other, null)) {
        return false;
      }
      if (ReferenceEquals(other, this)) {
        return true;
      }
      if (UniqueId != other.UniqueId) return false;
      if (Param != other.Param) return false;
      if (Name != other.Name) return false;
      if (Lv != other.Lv) return false;
      if (Value != other.Value) return false;
      if (IconId != other.IconId) return false;
      if (FrameId != other.FrameId) return false;
      if (TitleId != other.TitleId) return false;
      if (ExtraParam != other.ExtraParam) return false;
      return Equals(_unknownFields, other._unknownFields);
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public override int GetHashCode() {
      int hash = 1;
      if (UniqueId != 0L) hash ^= UniqueId.GetHashCode();
      if (Param != 0) hash ^= Param.GetHashCode();
      if (Name.Length != 0) hash ^= Name.GetHashCode();
      if (Lv != 0) hash ^= Lv.GetHashCode();
      if (Value != 0L) hash ^= Value.GetHashCode();
      if (IconId != 0) hash ^= IconId.GetHashCode();
      if (FrameId != 0) hash ^= FrameId.GetHashCode();
      if (TitleId != 0) hash ^= TitleId.GetHashCode();
      if (ExtraParam != 0) hash ^= ExtraParam.GetHashCode();
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
      if (UniqueId != 0L) {
        output.WriteRawTag(8);
        output.WriteInt64(UniqueId);
      }
      if (Param != 0) {
        output.WriteRawTag(16);
        output.WriteInt32(Param);
      }
      if (Name.Length != 0) {
        output.WriteRawTag(26);
        output.WriteString(Name);
      }
      if (Lv != 0) {
        output.WriteRawTag(32);
        output.WriteInt32(Lv);
      }
      if (Value != 0L) {
        output.WriteRawTag(40);
        output.WriteInt64(Value);
      }
      if (IconId != 0) {
        output.WriteRawTag(48);
        output.WriteInt32(IconId);
      }
      if (FrameId != 0) {
        output.WriteRawTag(56);
        output.WriteInt32(FrameId);
      }
      if (TitleId != 0) {
        output.WriteRawTag(64);
        output.WriteInt32(TitleId);
      }
      if (ExtraParam != 0) {
        output.WriteRawTag(72);
        output.WriteInt32(ExtraParam);
      }
      if (_unknownFields != null) {
        _unknownFields.WriteTo(output);
      }
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public int CalculateSize() {
      int size = 0;
      if (UniqueId != 0L) {
        size += 1 + pb::CodedOutputStream.ComputeInt64Size(UniqueId);
      }
      if (Param != 0) {
        size += 1 + pb::CodedOutputStream.ComputeInt32Size(Param);
      }
      if (Name.Length != 0) {
        size += 1 + pb::CodedOutputStream.ComputeStringSize(Name);
      }
      if (Lv != 0) {
        size += 1 + pb::CodedOutputStream.ComputeInt32Size(Lv);
      }
      if (Value != 0L) {
        size += 1 + pb::CodedOutputStream.ComputeInt64Size(Value);
      }
      if (IconId != 0) {
        size += 1 + pb::CodedOutputStream.ComputeInt32Size(IconId);
      }
      if (FrameId != 0) {
        size += 1 + pb::CodedOutputStream.ComputeInt32Size(FrameId);
      }
      if (TitleId != 0) {
        size += 1 + pb::CodedOutputStream.ComputeInt32Size(TitleId);
      }
      if (ExtraParam != 0) {
        size += 1 + pb::CodedOutputStream.ComputeInt32Size(ExtraParam);
      }
      if (_unknownFields != null) {
        size += _unknownFields.CalculateSize();
      }
      return size;
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public void MergeFrom(RankDtoProto other) {
      if (other == null) {
        return;
      }
      if (other.UniqueId != 0L) {
        UniqueId = other.UniqueId;
      }
      if (other.Param != 0) {
        Param = other.Param;
      }
      if (other.Name.Length != 0) {
        Name = other.Name;
      }
      if (other.Lv != 0) {
        Lv = other.Lv;
      }
      if (other.Value != 0L) {
        Value = other.Value;
      }
      if (other.IconId != 0) {
        IconId = other.IconId;
      }
      if (other.FrameId != 0) {
        FrameId = other.FrameId;
      }
      if (other.TitleId != 0) {
        TitleId = other.TitleId;
      }
      if (other.ExtraParam != 0) {
        ExtraParam = other.ExtraParam;
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
            UniqueId = input.ReadInt64();
            break;
          }
          case 16: {
            Param = input.ReadInt32();
            break;
          }
          case 26: {
            Name = input.ReadString();
            break;
          }
          case 32: {
            Lv = input.ReadInt32();
            break;
          }
          case 40: {
            Value = input.ReadInt64();
            break;
          }
          case 48: {
            IconId = input.ReadInt32();
            break;
          }
          case 56: {
            FrameId = input.ReadInt32();
            break;
          }
          case 64: {
            TitleId = input.ReadInt32();
            break;
          }
          case 72: {
            ExtraParam = input.ReadInt32();
            break;
          }
        }
      }
    }

  }

  /// <summary>
  /// 请求排行榜信息		msgId=142101
  /// </summary>
  public sealed partial class ReqRankInfoProto : pb::IMessage<ReqRankInfoProto> {
    private static readonly pb::MessageParser<ReqRankInfoProto> _parser = new pb::MessageParser<ReqRankInfoProto>(() => new ReqRankInfoProto());
    private pb::UnknownFieldSet _unknownFields;
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public static pb::MessageParser<ReqRankInfoProto> Parser { get { return _parser; } }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public static pbr::MessageDescriptor Descriptor {
      get { return global::Com.Proto.RankMessage.RankReflection.Descriptor.MessageTypes[1]; }
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    pbr::MessageDescriptor pb::IMessage.Descriptor {
      get { return Descriptor; }
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public ReqRankInfoProto() {
      OnConstruction();
    }

    partial void OnConstruction();

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public ReqRankInfoProto(ReqRankInfoProto other) : this() {
      rankType_ = other.rankType_;
      subType_ = other.subType_;
      _unknownFields = pb::UnknownFieldSet.Clone(other._unknownFields);
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public ReqRankInfoProto Clone() {
      return new ReqRankInfoProto(this);
    }

    /// <summary>Field number for the "rankType" field.</summary>
    public const int RankTypeFieldNumber = 1;
    private int rankType_;
    /// <summary>
    ///排行榜类型
    /// </summary>
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public int RankType {
      get { return rankType_; }
      set {
        rankType_ = value;
      }
    }

    /// <summary>Field number for the "subType" field.</summary>
    public const int SubTypeFieldNumber = 2;
    private int subType_;
    /// <summary>
    ///排行榜子类型
    /// </summary>
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public int SubType {
      get { return subType_; }
      set {
        subType_ = value;
      }
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public override bool Equals(object other) {
      return Equals(other as ReqRankInfoProto);
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public bool Equals(ReqRankInfoProto other) {
      if (ReferenceEquals(other, null)) {
        return false;
      }
      if (ReferenceEquals(other, this)) {
        return true;
      }
      if (RankType != other.RankType) return false;
      if (SubType != other.SubType) return false;
      return Equals(_unknownFields, other._unknownFields);
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public override int GetHashCode() {
      int hash = 1;
      if (RankType != 0) hash ^= RankType.GetHashCode();
      if (SubType != 0) hash ^= SubType.GetHashCode();
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
      if (RankType != 0) {
        output.WriteRawTag(8);
        output.WriteInt32(RankType);
      }
      if (SubType != 0) {
        output.WriteRawTag(16);
        output.WriteInt32(SubType);
      }
      if (_unknownFields != null) {
        _unknownFields.WriteTo(output);
      }
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public int CalculateSize() {
      int size = 0;
      if (RankType != 0) {
        size += 1 + pb::CodedOutputStream.ComputeInt32Size(RankType);
      }
      if (SubType != 0) {
        size += 1 + pb::CodedOutputStream.ComputeInt32Size(SubType);
      }
      if (_unknownFields != null) {
        size += _unknownFields.CalculateSize();
      }
      return size;
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public void MergeFrom(ReqRankInfoProto other) {
      if (other == null) {
        return;
      }
      if (other.RankType != 0) {
        RankType = other.RankType;
      }
      if (other.SubType != 0) {
        SubType = other.SubType;
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
            RankType = input.ReadInt32();
            break;
          }
          case 16: {
            SubType = input.ReadInt32();
            break;
          }
        }
      }
    }

  }

  /// <summary>
  /// 响应排行榜信息		msgId=142201
  /// </summary>
  public sealed partial class ResRankInfoProto : pb::IMessage<ResRankInfoProto> {
    private static readonly pb::MessageParser<ResRankInfoProto> _parser = new pb::MessageParser<ResRankInfoProto>(() => new ResRankInfoProto());
    private pb::UnknownFieldSet _unknownFields;
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public static pb::MessageParser<ResRankInfoProto> Parser { get { return _parser; } }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public static pbr::MessageDescriptor Descriptor {
      get { return global::Com.Proto.RankMessage.RankReflection.Descriptor.MessageTypes[2]; }
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    pbr::MessageDescriptor pb::IMessage.Descriptor {
      get { return Descriptor; }
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public ResRankInfoProto() {
      OnConstruction();
    }

    partial void OnConstruction();

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public ResRankInfoProto(ResRankInfoProto other) : this() {
      rankType_ = other.rankType_;
      subType_ = other.subType_;
      rankList_ = other.rankList_.Clone();
      self_ = other.self_ != null ? other.self_.Clone() : null;
      _unknownFields = pb::UnknownFieldSet.Clone(other._unknownFields);
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public ResRankInfoProto Clone() {
      return new ResRankInfoProto(this);
    }

    /// <summary>Field number for the "rankType" field.</summary>
    public const int RankTypeFieldNumber = 1;
    private int rankType_;
    /// <summary>
    ///排行榜类型 
    /// </summary>
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public int RankType {
      get { return rankType_; }
      set {
        rankType_ = value;
      }
    }

    /// <summary>Field number for the "subType" field.</summary>
    public const int SubTypeFieldNumber = 2;
    private int subType_;
    /// <summary>
    ///排行榜子类型
    /// </summary>
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public int SubType {
      get { return subType_; }
      set {
        subType_ = value;
      }
    }

    /// <summary>Field number for the "rankList" field.</summary>
    public const int RankListFieldNumber = 3;
    private static readonly pb::FieldCodec<global::Com.Proto.RankMessage.RankDtoProto> _repeated_rankList_codec
        = pb::FieldCodec.ForMessage(26, global::Com.Proto.RankMessage.RankDtoProto.Parser);
    private readonly pbc::RepeatedField<global::Com.Proto.RankMessage.RankDtoProto> rankList_ = new pbc::RepeatedField<global::Com.Proto.RankMessage.RankDtoProto>();
    /// <summary>
    ///排行榜数据
    /// </summary>
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public pbc::RepeatedField<global::Com.Proto.RankMessage.RankDtoProto> RankList {
      get { return rankList_; }
    }

    /// <summary>Field number for the "self" field.</summary>
    public const int SelfFieldNumber = 4;
    private global::Com.Proto.RankMessage.RankDtoProto self_;
    /// <summary>
    ///自己数据
    /// </summary>
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public global::Com.Proto.RankMessage.RankDtoProto Self {
      get { return self_; }
      set {
        self_ = value;
      }
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public override bool Equals(object other) {
      return Equals(other as ResRankInfoProto);
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public bool Equals(ResRankInfoProto other) {
      if (ReferenceEquals(other, null)) {
        return false;
      }
      if (ReferenceEquals(other, this)) {
        return true;
      }
      if (RankType != other.RankType) return false;
      if (SubType != other.SubType) return false;
      if(!rankList_.Equals(other.rankList_)) return false;
      if (!object.Equals(Self, other.Self)) return false;
      return Equals(_unknownFields, other._unknownFields);
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public override int GetHashCode() {
      int hash = 1;
      if (RankType != 0) hash ^= RankType.GetHashCode();
      if (SubType != 0) hash ^= SubType.GetHashCode();
      hash ^= rankList_.GetHashCode();
      if (self_ != null) hash ^= Self.GetHashCode();
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
      if (RankType != 0) {
        output.WriteRawTag(8);
        output.WriteInt32(RankType);
      }
      if (SubType != 0) {
        output.WriteRawTag(16);
        output.WriteInt32(SubType);
      }
      rankList_.WriteTo(output, _repeated_rankList_codec);
      if (self_ != null) {
        output.WriteRawTag(34);
        output.WriteMessage(Self);
      }
      if (_unknownFields != null) {
        _unknownFields.WriteTo(output);
      }
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public int CalculateSize() {
      int size = 0;
      if (RankType != 0) {
        size += 1 + pb::CodedOutputStream.ComputeInt32Size(RankType);
      }
      if (SubType != 0) {
        size += 1 + pb::CodedOutputStream.ComputeInt32Size(SubType);
      }
      size += rankList_.CalculateSize(_repeated_rankList_codec);
      if (self_ != null) {
        size += 1 + pb::CodedOutputStream.ComputeMessageSize(Self);
      }
      if (_unknownFields != null) {
        size += _unknownFields.CalculateSize();
      }
      return size;
    }

    [global::System.Diagnostics.DebuggerNonUserCodeAttribute]
    public void MergeFrom(ResRankInfoProto other) {
      if (other == null) {
        return;
      }
      if (other.RankType != 0) {
        RankType = other.RankType;
      }
      if (other.SubType != 0) {
        SubType = other.SubType;
      }
      rankList_.Add(other.rankList_);
      if (other.self_ != null) {
        if (self_ == null) {
          Self = new global::Com.Proto.RankMessage.RankDtoProto();
        }
        Self.MergeFrom(other.Self);
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
            RankType = input.ReadInt32();
            break;
          }
          case 16: {
            SubType = input.ReadInt32();
            break;
          }
          case 26: {
            rankList_.AddEntriesFrom(input, _repeated_rankList_codec);
            break;
          }
          case 34: {
            if (self_ == null) {
              Self = new global::Com.Proto.RankMessage.RankDtoProto();
            }
            input.ReadMessage(Self);
            break;
          }
        }
      }
    }

  }

  #endregion

}

#endregion Designer generated code