package net.digitalingot.feather.serverapi.velocity;

import java.lang.reflect.Proxy;
import net.digitalingot.feather.serverapi.api.FeatherService;
import net.digitalingot.feather.serverapi.api.event.EventService;
import net.digitalingot.feather.serverapi.api.meta.MetaService;
import net.digitalingot.feather.serverapi.api.player.PlayerService;
import net.digitalingot.feather.serverapi.api.ui.UIService;
import net.digitalingot.feather.serverapi.api.waypoint.WaypointService;
import org.jetbrains.annotations.NotNull;

public final class VelocityFeatherService implements FeatherService {
  private final EventService eventService = createUnsupported(EventService.class);
  private final PlayerService playerService = createUnsupported(PlayerService.class);
  private final UIService uiService = createUnsupported(UIService.class);
  private final WaypointService waypointService = createUnsupported(WaypointService.class);
  private final MetaService metaService = createUnsupported(MetaService.class);

  @Override
  public @NotNull EventService getEventService() {
    return eventService;
  }

  @Override
  public @NotNull PlayerService getPlayerService() {
    return playerService;
  }

  @Override
  public @NotNull UIService getUIService() {
    return uiService;
  }

  @Override
  public @NotNull WaypointService getWaypointService() {
    return waypointService;
  }

  @Override
  public @NotNull MetaService getMetaService() {
    return metaService;
  }

  private static <T> T createUnsupported(Class<T> type) {
    return type.cast(
        Proxy.newProxyInstance(
            type.getClassLoader(),
            new Class[] {type},
            (proxy, method, args) -> {
              throw new UnsupportedOperationException(
                  "Velocity implementation for " + type.getSimpleName() + " is not available yet.");
            }));
  }
}
