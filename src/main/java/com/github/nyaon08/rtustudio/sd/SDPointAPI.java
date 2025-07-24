package com.github.nyaon08.rtustudio.sd;

import java.util.UUID;

/**
 * 플러그인의 SDPoint 시스템과 상호작용할 수 있는 메서드를 제공하는 클래스입니다.
 */
public class SDPointAPI {

    private static SDPoint plugin;

    private SDPointAPI() {
        throw new UnsupportedOperationException("SDPointAPI is not instantiable");
    }

    /**
     * 플러그인 인스턴스를 반환합니다.
     *
     * @return 플러그인 인스턴스
     */
    private static SDPoint plugin() {
        if (plugin == null) plugin = SDPoint.getInstance();
        return plugin;
    }

    /**
     * 특정 플레이어의 포인트 값을 반환합니다.
     *
     * @param uuid 플레이의 UUID
     * @return 플레이어의 포인트
     */
    public static int getPoint(UUID uuid) {
        return plugin().getPointManager().getPoint(uuid);
    }

    /**
     * 특정 플레이어의 포인트를 추가하고 상호작용합니다.
     *
     * @param uuid  플레이어의 UUID
     * @param point 포인트
     */
    public static void addPoint(UUID uuid, int point) {
        plugin().getPointManager().addPoint(uuid, point);
    }

}
