package net.digitalingot.feather.serverapi.velocity;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import com.velocitypowered.api.proxy.messages.MinecraftChannelIdentifier;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import java.util.logging.Logger;
import net.digitalingot.feather.serverapi.api.FeatherAPI;

@Plugin(
    id = "feather-server-api",
    name = "FeatherServerAPI",
    version = "0.0.1-SNAPSHOT",
    authors = {"Feather"})
public final class FeatherVelocityPlugin {
  private static final MinecraftChannelIdentifier CHANNEL =
      MinecraftChannelIdentifier.from("feather:client");
  private static final MinecraftChannelIdentifier CHANNEL_FRAGMENTED =
      MinecraftChannelIdentifier.from("feather:client/frag");

  private final ProxyServer server;
  private final Logger logger;

  @Inject
  public FeatherVelocityPlugin(ProxyServer server, Logger logger) {
    this.server = server;
    this.logger = logger;
  }

  @Subscribe
  public void onProxyInitialize(ProxyInitializeEvent event) {
    server.getChannelRegistrar().register(CHANNEL, CHANNEL_FRAGMENTED);
    FeatherAPI.register(new VelocityFeatherService());

    logger.info(() -> "FeatherServerAPI initialized on Velocity " + server.getVersion().getVersion());
  }

  @Subscribe
  public void onProxyShutdown(ProxyShutdownEvent event) {
    server.getChannelRegistrar().unregister(CHANNEL, CHANNEL_FRAGMENTED);
  }
}
