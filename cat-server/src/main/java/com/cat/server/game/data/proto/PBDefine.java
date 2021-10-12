// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: PBProtocol.proto

package com.cat.server.game.data.proto;

public final class PBDefine {
  private PBDefine() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  /**
   * <pre>
   *生成规则
   *请求协议号=模块id*1000+请求协议号
   *返回协议号=模块id*1000+100+返回协议号
   * </pre>
   *
   * Protobuf enum {@code Protocol.PBProtocol}
   */
  public enum PBProtocol
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>DEFAULTID = 0;</code>
     */
    DEFAULTID(0),
    /**
     * <pre>
     *Item
     * </pre>
     *
     * <code>ReqItemUse = 102101;</code>
     */
    ReqItemUse(102101),
    /**
     * <code>RespItemUpdate = 102201;</code>
     */
    RespItemUpdate(102201),
    /**
     * <code>RespItemUse = 102202;</code>
     */
    RespItemUse(102202),
    /**
     * <code>RespItemDelete = 102203;</code>
     */
    RespItemDelete(102203),
    /**
     * <code>ReqItemSell = 102102;</code>
     */
    ReqItemSell(102102),
    /**
     * <code>RespRewards = 102204;</code>
     */
    RespRewards(102204),
    /**
     * <code>RespItemSell = 102205;</code>
     */
    RespItemSell(102205),
    /**
     * <pre>
     *Player
     * </pre>
     *
     * <code>RespPlayerCreateRole = 101201;</code>
     */
    RespPlayerCreateRole(101201),
    /**
     * <code>ReqPlayerCreateRole = 101101;</code>
     */
    ReqPlayerCreateRole(101101),
    /**
     * <code>ReqPlayerReLogin = 101102;</code>
     */
    ReqPlayerReLogin(101102),
    /**
     * <code>RespPlayerHeart = 101202;</code>
     */
    RespPlayerHeart(101202),
    /**
     * <code>RespInitPlayerInfo = 101203;</code>
     */
    RespInitPlayerInfo(101203),
    /**
     * <code>RespPlayerRandName = 101204;</code>
     */
    RespPlayerRandName(101204),
    /**
     * <code>RespPlayerDisconnect = 101205;</code>
     */
    RespPlayerDisconnect(101205),
    /**
     * <code>RespPlayerEnterGame = 101206;</code>
     */
    RespPlayerEnterGame(101206),
    /**
     * <code>RespPlayerReLogin = 101207;</code>
     */
    RespPlayerReLogin(101207),
    /**
     * <code>RespPlayerLogin = 101208;</code>
     */
    RespPlayerLogin(101208),
    /**
     * <code>ReqPlayerRandName = 101103;</code>
     */
    ReqPlayerRandName(101103),
    /**
     * <code>RespUpdatePlayerInfo = 101209;</code>
     */
    RespUpdatePlayerInfo(101209),
    /**
     * <code>ReqPlayerLogin = 101104;</code>
     */
    ReqPlayerLogin(101104),
    /**
     * <code>ReqPlayerHeart = 101105;</code>
     */
    ReqPlayerHeart(101105),
    /**
     * <pre>
     *LearnCommunity
     * </pre>
     *
     * <code>RespLearnCommunityInfo = 200201;</code>
     */
    RespLearnCommunityInfo(200201),
    /**
     * <code>RespLearnCommunityReward = 200202;</code>
     */
    RespLearnCommunityReward(200202),
    /**
     * <code>ReqLearnCommunityInfo = 200101;</code>
     */
    ReqLearnCommunityInfo(200101),
    /**
     * <code>ReqLearnCommunityReward = 200102;</code>
     */
    ReqLearnCommunityReward(200102),
    /**
     * <pre>
     *Mail
     * </pre>
     *
     * <code>RespMailRead = 103201;</code>
     */
    RespMailRead(103201),
    /**
     * <code>ReqMailReward = 103101;</code>
     */
    ReqMailReward(103101),
    /**
     * <code>RespMailDelete = 103202;</code>
     */
    RespMailDelete(103202),
    /**
     * <code>ReqMailRead = 103102;</code>
     */
    ReqMailRead(103102),
    /**
     * <code>ReqMailList = 103103;</code>
     */
    ReqMailList(103103),
    /**
     * <code>RespMailReward = 103203;</code>
     */
    RespMailReward(103203),
    /**
     * <code>RespMailList = 103204;</code>
     */
    RespMailList(103204),
    /**
     * <code>ReqMailDelete = 103104;</code>
     */
    ReqMailDelete(103104),
    /**
     * <pre>
     *Chat
     * </pre>
     *
     * <code>RespChat = 104201;</code>
     */
    RespChat(104201),
    /**
     * <code>ReqChat = 104101;</code>
     */
    ReqChat(104101),
    /**
     * <pre>
     *Activity
     * </pre>
     *
     * <code>ReqActivityInfo = 105101;</code>
     */
    ReqActivityInfo(105101),
    /**
     * <code>RespActivityInfo = 105201;</code>
     */
    RespActivityInfo(105201),
    /**
     * <code>RespActivityInfoUpdate = 105202;</code>
     */
    RespActivityInfoUpdate(105202),
    /**
     * <pre>
     *Login
     * </pre>
     *
     * <code>ReqLogin = 100101;</code>
     */
    ReqLogin(100101),
    /**
     * <code>ReqRandName = 100102;</code>
     */
    ReqRandName(100102),
    /**
     * <code>ReqCreateRole = 100103;</code>
     */
    ReqCreateRole(100103),
    /**
     * <pre>
     *Common
     * </pre>
     *
     * <code>RespTips = 1201;</code>
     */
    RespTips(1201),
    UNRECOGNIZED(-1),
    ;

    /**
     * <code>DEFAULTID = 0;</code>
     */
    public static final int DEFAULTID_VALUE = 0;
    /**
     * <pre>
     *Item
     * </pre>
     *
     * <code>ReqItemUse = 102101;</code>
     */
    public static final int ReqItemUse_VALUE = 102101;
    /**
     * <code>RespItemUpdate = 102201;</code>
     */
    public static final int RespItemUpdate_VALUE = 102201;
    /**
     * <code>RespItemUse = 102202;</code>
     */
    public static final int RespItemUse_VALUE = 102202;
    /**
     * <code>RespItemDelete = 102203;</code>
     */
    public static final int RespItemDelete_VALUE = 102203;
    /**
     * <code>ReqItemSell = 102102;</code>
     */
    public static final int ReqItemSell_VALUE = 102102;
    /**
     * <code>RespRewards = 102204;</code>
     */
    public static final int RespRewards_VALUE = 102204;
    /**
     * <code>RespItemSell = 102205;</code>
     */
    public static final int RespItemSell_VALUE = 102205;
    /**
     * <pre>
     *Player
     * </pre>
     *
     * <code>RespPlayerCreateRole = 101201;</code>
     */
    public static final int RespPlayerCreateRole_VALUE = 101201;
    /**
     * <code>ReqPlayerCreateRole = 101101;</code>
     */
    public static final int ReqPlayerCreateRole_VALUE = 101101;
    /**
     * <code>ReqPlayerReLogin = 101102;</code>
     */
    public static final int ReqPlayerReLogin_VALUE = 101102;
    /**
     * <code>RespPlayerHeart = 101202;</code>
     */
    public static final int RespPlayerHeart_VALUE = 101202;
    /**
     * <code>RespInitPlayerInfo = 101203;</code>
     */
    public static final int RespInitPlayerInfo_VALUE = 101203;
    /**
     * <code>RespPlayerRandName = 101204;</code>
     */
    public static final int RespPlayerRandName_VALUE = 101204;
    /**
     * <code>RespPlayerDisconnect = 101205;</code>
     */
    public static final int RespPlayerDisconnect_VALUE = 101205;
    /**
     * <code>RespPlayerEnterGame = 101206;</code>
     */
    public static final int RespPlayerEnterGame_VALUE = 101206;
    /**
     * <code>RespPlayerReLogin = 101207;</code>
     */
    public static final int RespPlayerReLogin_VALUE = 101207;
    /**
     * <code>RespPlayerLogin = 101208;</code>
     */
    public static final int RespPlayerLogin_VALUE = 101208;
    /**
     * <code>ReqPlayerRandName = 101103;</code>
     */
    public static final int ReqPlayerRandName_VALUE = 101103;
    /**
     * <code>RespUpdatePlayerInfo = 101209;</code>
     */
    public static final int RespUpdatePlayerInfo_VALUE = 101209;
    /**
     * <code>ReqPlayerLogin = 101104;</code>
     */
    public static final int ReqPlayerLogin_VALUE = 101104;
    /**
     * <code>ReqPlayerHeart = 101105;</code>
     */
    public static final int ReqPlayerHeart_VALUE = 101105;
    /**
     * <pre>
     *LearnCommunity
     * </pre>
     *
     * <code>RespLearnCommunityInfo = 200201;</code>
     */
    public static final int RespLearnCommunityInfo_VALUE = 200201;
    /**
     * <code>RespLearnCommunityReward = 200202;</code>
     */
    public static final int RespLearnCommunityReward_VALUE = 200202;
    /**
     * <code>ReqLearnCommunityInfo = 200101;</code>
     */
    public static final int ReqLearnCommunityInfo_VALUE = 200101;
    /**
     * <code>ReqLearnCommunityReward = 200102;</code>
     */
    public static final int ReqLearnCommunityReward_VALUE = 200102;
    /**
     * <pre>
     *Mail
     * </pre>
     *
     * <code>RespMailRead = 103201;</code>
     */
    public static final int RespMailRead_VALUE = 103201;
    /**
     * <code>ReqMailReward = 103101;</code>
     */
    public static final int ReqMailReward_VALUE = 103101;
    /**
     * <code>RespMailDelete = 103202;</code>
     */
    public static final int RespMailDelete_VALUE = 103202;
    /**
     * <code>ReqMailRead = 103102;</code>
     */
    public static final int ReqMailRead_VALUE = 103102;
    /**
     * <code>ReqMailList = 103103;</code>
     */
    public static final int ReqMailList_VALUE = 103103;
    /**
     * <code>RespMailReward = 103203;</code>
     */
    public static final int RespMailReward_VALUE = 103203;
    /**
     * <code>RespMailList = 103204;</code>
     */
    public static final int RespMailList_VALUE = 103204;
    /**
     * <code>ReqMailDelete = 103104;</code>
     */
    public static final int ReqMailDelete_VALUE = 103104;
    /**
     * <pre>
     *Chat
     * </pre>
     *
     * <code>RespChat = 104201;</code>
     */
    public static final int RespChat_VALUE = 104201;
    /**
     * <code>ReqChat = 104101;</code>
     */
    public static final int ReqChat_VALUE = 104101;
    /**
     * <pre>
     *Activity
     * </pre>
     *
     * <code>ReqActivityInfo = 105101;</code>
     */
    public static final int ReqActivityInfo_VALUE = 105101;
    /**
     * <code>RespActivityInfo = 105201;</code>
     */
    public static final int RespActivityInfo_VALUE = 105201;
    /**
     * <code>RespActivityInfoUpdate = 105202;</code>
     */
    public static final int RespActivityInfoUpdate_VALUE = 105202;
    /**
     * <pre>
     *Login
     * </pre>
     *
     * <code>ReqLogin = 100101;</code>
     */
    public static final int ReqLogin_VALUE = 100101;
    /**
     * <code>ReqRandName = 100102;</code>
     */
    public static final int ReqRandName_VALUE = 100102;
    /**
     * <code>ReqCreateRole = 100103;</code>
     */
    public static final int ReqCreateRole_VALUE = 100103;
    /**
     * <pre>
     *Common
     * </pre>
     *
     * <code>RespTips = 1201;</code>
     */
    public static final int RespTips_VALUE = 1201;


    public final int getNumber() {
      if (this == UNRECOGNIZED) {
        throw new java.lang.IllegalArgumentException(
            "Can't get the number of an unknown enum value.");
      }
      return value;
    }

    /**
     * @param value The numeric wire value of the corresponding enum entry.
     * @return The enum associated with the given numeric wire value.
     * @deprecated Use {@link #forNumber(int)} instead.
     */
    @java.lang.Deprecated
    public static PBProtocol valueOf(int value) {
      return forNumber(value);
    }

    /**
     * @param value The numeric wire value of the corresponding enum entry.
     * @return The enum associated with the given numeric wire value.
     */
    public static PBProtocol forNumber(int value) {
      switch (value) {
        case 0: return DEFAULTID;
        case 102101: return ReqItemUse;
        case 102201: return RespItemUpdate;
        case 102202: return RespItemUse;
        case 102203: return RespItemDelete;
        case 102102: return ReqItemSell;
        case 102204: return RespRewards;
        case 102205: return RespItemSell;
        case 101201: return RespPlayerCreateRole;
        case 101101: return ReqPlayerCreateRole;
        case 101102: return ReqPlayerReLogin;
        case 101202: return RespPlayerHeart;
        case 101203: return RespInitPlayerInfo;
        case 101204: return RespPlayerRandName;
        case 101205: return RespPlayerDisconnect;
        case 101206: return RespPlayerEnterGame;
        case 101207: return RespPlayerReLogin;
        case 101208: return RespPlayerLogin;
        case 101103: return ReqPlayerRandName;
        case 101209: return RespUpdatePlayerInfo;
        case 101104: return ReqPlayerLogin;
        case 101105: return ReqPlayerHeart;
        case 200201: return RespLearnCommunityInfo;
        case 200202: return RespLearnCommunityReward;
        case 200101: return ReqLearnCommunityInfo;
        case 200102: return ReqLearnCommunityReward;
        case 103201: return RespMailRead;
        case 103101: return ReqMailReward;
        case 103202: return RespMailDelete;
        case 103102: return ReqMailRead;
        case 103103: return ReqMailList;
        case 103203: return RespMailReward;
        case 103204: return RespMailList;
        case 103104: return ReqMailDelete;
        case 104201: return RespChat;
        case 104101: return ReqChat;
        case 105101: return ReqActivityInfo;
        case 105201: return RespActivityInfo;
        case 105202: return RespActivityInfoUpdate;
        case 100101: return ReqLogin;
        case 100102: return ReqRandName;
        case 100103: return ReqCreateRole;
        case 1201: return RespTips;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<PBProtocol>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static final com.google.protobuf.Internal.EnumLiteMap<
        PBProtocol> internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<PBProtocol>() {
            public PBProtocol findValueByNumber(int number) {
              return PBProtocol.forNumber(number);
            }
          };

    public final com.google.protobuf.Descriptors.EnumValueDescriptor
        getValueDescriptor() {
      return getDescriptor().getValues().get(ordinal());
    }
    public final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }
    public static final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptor() {
      return com.cat.server.game.data.proto.PBDefine.getDescriptor().getEnumTypes().get(0);
    }

    private static final PBProtocol[] VALUES = values();

    public static PBProtocol valueOf(
        com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException(
          "EnumValueDescriptor is not for this type.");
      }
      if (desc.getIndex() == -1) {
        return UNRECOGNIZED;
      }
      return VALUES[desc.getIndex()];
    }

    private final int value;

    private PBProtocol(int value) {
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:Protocol.PBProtocol)
  }


  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\020PBProtocol.proto\022\010Protocol*\331\007\n\nPBProto" +
      "col\022\r\n\tDEFAULTID\020\000\022\020\n\nReqItemUse\020\325\235\006\022\024\n\016" +
      "RespItemUpdate\020\271\236\006\022\021\n\013RespItemUse\020\272\236\006\022\024\n" +
      "\016RespItemDelete\020\273\236\006\022\021\n\013ReqItemSell\020\326\235\006\022\021" +
      "\n\013RespRewards\020\274\236\006\022\022\n\014RespItemSell\020\275\236\006\022\032\n" +
      "\024RespPlayerCreateRole\020\321\226\006\022\031\n\023ReqPlayerCr" +
      "eateRole\020\355\225\006\022\026\n\020ReqPlayerReLogin\020\356\225\006\022\025\n\017" +
      "RespPlayerHeart\020\322\226\006\022\030\n\022RespInitPlayerInf" +
      "o\020\323\226\006\022\030\n\022RespPlayerRandName\020\324\226\006\022\032\n\024RespP" +
      "layerDisconnect\020\325\226\006\022\031\n\023RespPlayerEnterGa" +
      "me\020\326\226\006\022\027\n\021RespPlayerReLogin\020\327\226\006\022\025\n\017RespP" +
      "layerLogin\020\330\226\006\022\027\n\021ReqPlayerRandName\020\357\225\006\022" +
      "\032\n\024RespUpdatePlayerInfo\020\331\226\006\022\024\n\016ReqPlayer" +
      "Login\020\360\225\006\022\024\n\016ReqPlayerHeart\020\361\225\006\022\034\n\026RespL" +
      "earnCommunityInfo\020\211\234\014\022\036\n\030RespLearnCommun" +
      "ityReward\020\212\234\014\022\033\n\025ReqLearnCommunityInfo\020\245" +
      "\233\014\022\035\n\027ReqLearnCommunityReward\020\246\233\014\022\022\n\014Res" +
      "pMailRead\020\241\246\006\022\023\n\rReqMailReward\020\275\245\006\022\024\n\016Re" +
      "spMailDelete\020\242\246\006\022\021\n\013ReqMailRead\020\276\245\006\022\021\n\013R" +
      "eqMailList\020\277\245\006\022\024\n\016RespMailReward\020\243\246\006\022\022\n\014" +
      "RespMailList\020\244\246\006\022\023\n\rReqMailDelete\020\300\245\006\022\016\n" +
      "\010RespChat\020\211\256\006\022\r\n\007ReqChat\020\245\255\006\022\025\n\017ReqActiv" +
      "ityInfo\020\215\265\006\022\026\n\020RespActivityInfo\020\361\265\006\022\034\n\026R" +
      "espActivityInfoUpdate\020\362\265\006\022\016\n\010ReqLogin\020\205\216" +
      "\006\022\021\n\013ReqRandName\020\206\216\006\022\023\n\rReqCreateRole\020\207\216" +
      "\006\022\r\n\010RespTips\020\261\tB*\n\036com.cat.server.game." +
      "data.protoB\010PBDefineb\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
