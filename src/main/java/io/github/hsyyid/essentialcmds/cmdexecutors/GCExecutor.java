/*
 * This file is part of EssentialCmds, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2015 - 2015 HassanS6000
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package io.github.hsyyid.essentialcmds.cmdexecutors;

import com.google.common.collect.Iterables;
import io.github.hsyyid.essentialcmds.internal.AsyncCommandExecutorBase;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.world.World;

import javax.annotation.Nonnull;

public class GCExecutor extends AsyncCommandExecutorBase
{
	@Override
	public void executeAsync(CommandSource src, CommandContext ctx)
	{
		double tps = Sponge.getServer().getTicksPerSecond();
		src.sendMessage(Text.of(TextColors.GOLD, "Current TPS: ", TextColors.GRAY, tps));
		src.sendMessage(Text.of(TextColors.GOLD, "World Info:"));

		for (World world : Sponge.getServer().getWorlds())
		{
			int numOfEntities = world.getEntities().size();
			int loadedChunks = Iterables.size(world.getLoadedChunks());
			src.sendMessage(Text.of());
			src.sendMessage(Text.of(TextColors.GREEN, "World: ", world.getName()));
			src.sendMessage(Text.of(TextColors.GOLD, "Entities: ", TextColors.GRAY, numOfEntities));
			src.sendMessage(Text.of(TextColors.GOLD, "Loaded Chunks: ", TextColors.GRAY, loadedChunks));
		}
	}

	@Nonnull
	@Override
	public String[] getAliases() {
		return new String[] { "gc", "tickstat" };
	}

	@Nonnull
	@Override
	public CommandSpec getSpec() {
		return CommandSpec.builder()
				.description(Text.of("TickStat Command"))
				.permission("essentialcmds.tickstat.use")
				.executor(this)
				.build();
	}
}
