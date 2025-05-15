package com.andrewgaming.nocewarn.mixin.client;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.packet.c2s.play.CommandExecutionC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ClientPlayNetworkHandler.class)
public abstract class ClientPlayNetworkHandlerMixin {
	@Shadow public abstract ClientConnection getConnection();

	@Overwrite
	public void runClickEventCommand(String command, boolean closeScreenOnRun) {
		this.getConnection().send(new CommandExecutionC2SPacket(command));
	}
}