package com.volmit.blockentities;


import com.volmit.blockentities.Commands.BlockCommand;
import com.volmit.blockentities.Commands.MainCommand;
import com.volmit.blockentities.Commands.MainCompleter;
import com.volmit.blockentities.Constants.Commands;
import com.volmit.blockentities.Listeners.BlockListener;
import com.volmit.blockentities.Managers.BlockManager;
import com.volmit.blockentities.Utils.Logger;
import com.volmit.blockentities.Utils.Settings;
import org.bukkit.plugin.java.JavaPlugin;

public class BlockEntities extends JavaPlugin {
    public Settings settings;

    @Override
    public void onEnable() {
        this.settings = new Settings(this);
        Logger.setPlugin(this);
        Logger.info("Enabled plugin PackManager");

        BlockManager blockManager = new BlockManager(this);
        getServer().getPluginManager().registerEvents(new BlockListener(this, blockManager), this);

        BlockCommand blockCommand = new BlockCommand(this);
        getCommand(Commands.BASE).setExecutor(new MainCommand(this, blockCommand));
        getCommand(Commands.BASE).setTabCompleter(new MainCompleter(blockCommand));
    }
}
