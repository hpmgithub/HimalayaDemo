package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class MyClass {
    public static void main(String[] args) {
        Schema schema = new Schema(1, "com.jash.himalayademo.entities");
        schema.setDefaultJavaPackageDao("com.jash.himalayademo.dao");
        //TODO 创建发现表
        Entity discoveryEntity = schema.addEntity("DiscoveryEntity");
        discoveryEntity.addIdProperty();
        discoveryEntity.addIntProperty("orderNum");
        discoveryEntity.addStringProperty("title");
        discoveryEntity.addStringProperty("subtitle");
        discoveryEntity.addStringProperty("coverPath");
        discoveryEntity.addStringProperty("contentType");
        discoveryEntity.addStringProperty("url");
        discoveryEntity.addStringProperty("sharePic");
        discoveryEntity.addBooleanProperty("enableShare");
        discoveryEntity.addBooleanProperty("isHot");
        discoveryEntity.addBooleanProperty("isExternalUrl");
        discoveryEntity.addIntProperty("contentUpdatedAt");

        //TODO 创建热点图
        Entity focusImageEntity = schema.addEntity("FocusImageEntity");
        focusImageEntity.addIdProperty();
        focusImageEntity.addStringProperty("shortTitle");
        focusImageEntity.addStringProperty("longTitle");
        focusImageEntity.addStringProperty("pic");
        focusImageEntity.addIntProperty("type");
        focusImageEntity.addIntProperty("specialId");
        focusImageEntity.addIntProperty("subType");
        focusImageEntity.addBooleanProperty("isShare");
        focusImageEntity.addBooleanProperty("is_External_url");

        // TODO: 创建专辑
        Entity albumEntity = schema.addEntity("AlbumEntity");
        albumEntity.addLongProperty("albumId").primaryKey();
        albumEntity.addIntProperty("categoryId");
        albumEntity.addStringProperty("title");
        albumEntity.addStringProperty("coverOrigin");
        albumEntity.addStringProperty("coverSmall");
        albumEntity.addStringProperty("coverMiddle");
        albumEntity.addStringProperty("coverLarge");
        albumEntity.addStringProperty("coverWebLarge");
        albumEntity.addDateProperty("createdAt");
        albumEntity.addDateProperty("updatedAt");
        albumEntity.addLongProperty("uid");
        albumEntity.addStringProperty("nickname");
        albumEntity.addBooleanProperty("isVerified");
        albumEntity.addStringProperty("avatarPath");
        albumEntity.addStringProperty("intro");
        albumEntity.addStringProperty("introRich");
        albumEntity.addStringProperty("tags");
        albumEntity.addIntProperty("shares");
        albumEntity.addBooleanProperty("hasNew");
        albumEntity.addBooleanProperty("isFavorite");
        albumEntity.addIntProperty("playTimes");
        albumEntity.addIntProperty("status");
        albumEntity.addIntProperty("serializeStatus");
        albumEntity.addIntProperty("serialState");
        albumEntity.addIntProperty("playTrackId");
        albumEntity.addBooleanProperty("isRecordDesc");

        // TODO: 创建音频
        Entity trackEntity = schema.addEntity("TrackEntity");
        trackEntity.addLongProperty("trackId").primaryKey();
        trackEntity.addLongProperty("uid");
        trackEntity.addStringProperty("playUrl64");
        trackEntity.addStringProperty("playUrl32");
        trackEntity.addStringProperty("downloadUrl");
        trackEntity.addStringProperty("playPathAacv164");
        trackEntity.addStringProperty("playPathAacv224");
        trackEntity.addStringProperty("downloadAacUrl");
        trackEntity.addStringProperty("title");
        trackEntity.addIntProperty("duration");
        trackEntity.addLongProperty("albumId");
        trackEntity.addIntProperty("processState");
        trackEntity.addDateProperty("createdAt");
        trackEntity.addStringProperty("coverSmall");
        trackEntity.addStringProperty("coverMiddle");
        trackEntity.addStringProperty("coverLarge");
        trackEntity.addStringProperty("nickname");
        trackEntity.addIntProperty("userSource");
        trackEntity.addIntProperty("orderNum");
        trackEntity.addBooleanProperty("isPublic");
        trackEntity.addIntProperty("likes");
        trackEntity.addIntProperty("playtimes");
        trackEntity.addIntProperty("comments");
        trackEntity.addIntProperty("shares");
        trackEntity.addIntProperty("downloadSize");
        trackEntity.addIntProperty("downloadAacSize");


//        title: "听新闻",
//                contentType: "album",
//                isFinished: false,
//                categoryId: 1,
//                count: 1000,
//                hasMore: true,
        Entity recommendEntity = schema.addEntity("RecommendEntity");
        recommendEntity.addIdProperty().autoincrement();
        recommendEntity.addStringProperty("title");
        recommendEntity.addStringProperty("contentType");
        recommendEntity.addBooleanProperty("isFinished");
        recommendEntity.addIntProperty("categoryId");
        recommendEntity.addIntProperty("count");
        recommendEntity.addBooleanProperty("hasMore");

        try {
            new DaoGenerator().generateAll(schema, "lib/java-gen");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
