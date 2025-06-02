package com.andrewgaming.nocewarn.mixin.client;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.ClientConnectionState;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientCommonNetworkHandler;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.packet.c2s.play.CommandExecutionC2SPacket;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = ClientPlayNetworkHandler.class, priority = 2000)
public abstract class ClientPlayNetworkHandlerMixin extends ClientCommonNetworkHandler {
	protected ClientPlayNetworkHandlerMixin(MinecraftClient client, ClientConnection connection, ClientConnectionState connectionState) {
		super(client, connection, connectionState);
	}

	@Shadow public abstract ClientConnection getConnection();

	@Overwrite
	public void runClickEventCommand(String command, @Nullable Screen afterActionScreen) {
		this.getConnection().send(new CommandExecutionC2SPacket(command));
		this.client.setScreen(afterActionScreen);
	}
}