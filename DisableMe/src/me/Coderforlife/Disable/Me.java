package me.Coderforlife.Disable;
import java.util.logging.Logger;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Me
extends JavaPlugin
{
final String prefix = ChatColor.RED + "[" + ChatColor.DARK_RED + "DisableMe" + ChatColor.RED + "]";
final String help = ChatColor.RED + "[" + ChatColor.DARK_RED + "DisableMe Help" + ChatColor.RED + "]";
final String dash = ChatColor.GRAY + "- ";
final String error = ChatColor.RED + "Error";
Logger logger = Logger.getLogger("Minecraft");

public void onEnable()
{
  PluginDescriptionFile pdffile = getDescription();
  this.logger.info(pdffile.getName() + ChatColor.RED + " Has Been Enabled." + "Version: " + pdffile.getVersion() + " Website: " + pdffile.getWebsite());
}

public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args)
{
  if ((sender instanceof Player))
  {
    Player player = (Player)sender;
    if (cmd.getName().equalsIgnoreCase("disableme"))
    {
      if (player.hasPermission("disableme.help"))
      {
        sender.sendMessage(ChatColor.RED + "-------------------" + this.help + "-------------------");
        sender.sendMessage(ChatColor.GREEN + "Enable" + this.dash + ChatColor.RED + " Enables all the plugins on the server.");
        sender.sendMessage(ChatColor.GREEN + "Enable " + ChatColor.GRAY + "<plugin> " + this.dash + ChatColor.RED + "Enables the plugin you choose.");
        sender.sendMessage(ChatColor.RED + "Disable" + this.dash + ChatColor.DARK_RED + " Disables all the plugins on the server.");
        sender.sendMessage(ChatColor.RED + "Disable " + ChatColor.GRAY + "<plugin> " + this.dash + ChatColor.DARK_RED + "Disables the plugin you choose.");
      }
    }
    else if (cmdLabel.equalsIgnoreCase("disable"))
    {
      if ((args.length == 0) && (player.hasPermission("disableme.disable.all")))
      {
        disableAll();
        sender.sendMessage(this.prefix + ChatColor.WHITE + " All Plugins " + ChatColor.RED + "disabled.");
      }
      else if ((args.length > 0) && (player.hasPermission("disableme.disable")))
      {
        if (disablePlugin(args[0])) {
          sender.sendMessage(this.prefix + ChatColor.RED + "The Plugin " + ChatColor.BOLD + args[0] + " is now disabled.");
        } else {
          sender.sendMessage(this.prefix + ChatColor.RED + " The Plugin " + ChatColor.GREEN + ChatColor.BOLD + args[0] + ChatColor.RED + " was not found!");
        }
      }
      else
      {
        sender.sendMessage(ChatColor.DARK_RED + "You Don't Have Permission To Do That!");
      }
    }
    else if (cmdLabel.equalsIgnoreCase("enable")) {
      if ((args.length == 0) && (player.hasPermission("disableme.enable.all")))
      {
        enableAll();
        sender.sendMessage(this.prefix + ChatColor.WHITE + " All Plugins " + ChatColor.GREEN + "enabled.");
      }
      else if ((args.length > 0) && (player.hasPermission("disableme.enable")))
      {
        if (enablePlugin(args[0])) {
          sender.sendMessage(this.prefix + ChatColor.RED + "The Plugin " + ChatColor.BOLD + args[0] + " is now enabled.");
        } else {
          sender.sendMessage(this.prefix + ChatColor.RED + " The Plugin " + ChatColor.GREEN + ChatColor.BOLD + args[0] + ChatColor.RED + " was not found!");
        }
      }
      else
      {
        sender.sendMessage(ChatColor.DARK_RED + "You Don't Have Permission To Do That!");
      }
    }
  }
  else if (cmdLabel.equalsIgnoreCase("disable"))
  {
    if (args.length == 0)
    {
      disableAll();
      sender.sendMessage(this.prefix + ChatColor.RED + " All Plugins disabled.");
    }
    else if (args.length > 0)
    {
      if (disablePlugin(args[0])) {
        sender.sendMessage(this.prefix + ChatColor.RED + "The Plugin " + ChatColor.BOLD + args[0] + " is now disabled.");
      } else {
        sender.sendMessage(this.prefix + ChatColor.RED + " The Plugin " + ChatColor.GREEN + ChatColor.BOLD + args[0] + ChatColor.RED + " was not found!");
      }
    }
  }
  else if (cmdLabel.equalsIgnoreCase("enable"))
  {
    if (args.length == 0)
    {
      enableAll();
      sender.sendMessage(this.prefix + ChatColor.RED + " All Plugins enabled.");
    }
    else if (args.length > 0)
    {
      if (enablePlugin(args[0])) {
        sender.sendMessage(this.prefix + ChatColor.RED + "The Plugin " + ChatColor.BOLD + args[0] + " is now enabled.");
      } else {
        sender.sendMessage(this.prefix + ChatColor.RED + " The Plugin " + ChatColor.GREEN + ChatColor.BOLD + args[0] + ChatColor.RED + " was not found!");
      }
    }
  }
  return true;
}

private void enableAll()
{
  Plugin[] arrayOfPlugin;
  int j = (arrayOfPlugin = getServer().getPluginManager().getPlugins()).length;
  for (int i = 0; i < j; i++)
  {
    Plugin p = arrayOfPlugin[i];
    getServer().getPluginManager().enablePlugin(p);
  }
}

private boolean enablePlugin(String pluginName)
{
  Plugin plugin = getServer().getPluginManager().getPlugin(pluginName);
  if (plugin != null)
  {
    getServer().getPluginManager().enablePlugin(plugin);
    return true;
  }
  return false;
}

private void disableAll()
{
  Plugin[] arrayOfPlugin;
  int j = (arrayOfPlugin = getServer().getPluginManager().getPlugins()).length;
  for (int i = 0; i < j; i++)
  {
    Plugin p = arrayOfPlugin[i];
    if (!p.getName().equalsIgnoreCase("disableme")) {
      getServer().getPluginManager().disablePlugin(p);
    }
  }
}

private boolean disablePlugin(String pluginName)
{
  Plugin plugin = getServer().getPluginManager().getPlugin(pluginName);
  if (plugin != null)
  {
    getServer().getPluginManager().disablePlugin(plugin);
    return true;
  }
  return false;
}
}
