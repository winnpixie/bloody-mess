package io.github.winnpixie.bloodymess.utilities;

import io.github.winnpixie.bloodymess.BloodyMess;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.EntityType;

public class BloodHelper {
    private static BlockData redstoneBlockData;
    private static BlockData coalBlockData;
    private static BlockData boneBlockData;
    private static BlockData ironBlockData;
    private static BlockData snowBlockData;
    private static BlockData tntBlockData;
    private static BlockData lavaBlockData;
    private static BlockData endPortalBlockData;

    public static void init(BloodyMess plugin) {
        redstoneBlockData = plugin.getServer().createBlockData(Material.REDSTONE_BLOCK);
        coalBlockData = plugin.getServer().createBlockData(Material.COAL_BLOCK);
        boneBlockData = plugin.getServer().createBlockData(Material.BONE_BLOCK);
        ironBlockData = plugin.getServer().createBlockData(Material.IRON_BLOCK);
        snowBlockData = plugin.getServer().createBlockData(Material.SNOW_BLOCK);
        tntBlockData = plugin.getServer().createBlockData(Material.TNT);
        lavaBlockData = plugin.getServer().createBlockData(Material.LAVA);
        endPortalBlockData = plugin.getServer().createBlockData(Material.END_PORTAL);
    }

    public static BlockData getBlockForEntity(EntityType type) {
        switch (type) {
            case SKELETON:
            case SKELETON_HORSE:
                return boneBlockData;
            case WITHER:
            case WITHER_SKELETON:
                return coalBlockData;
            case IRON_GOLEM:
                return ironBlockData;
            case SNOW_GOLEM:
                return snowBlockData;
            case CREEPER:
                return tntBlockData;
            case BLAZE:
            case MAGMA_CUBE:
            case STRIDER:
                return lavaBlockData;
            case ENDER_DRAGON:
            case ENDERMAN:
            case ENDERMITE:
                return endPortalBlockData;
            case ARMOR_STAND:
                return null; // Living, but not *really*...
            default:
                return redstoneBlockData;
        }
    }
}