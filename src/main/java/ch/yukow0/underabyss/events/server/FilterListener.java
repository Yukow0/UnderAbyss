package ch.yukow0.underabyss.events.server;

import ch.yukow0.underabyss.Component.HasFilter;
import ch.yukow0.underabyss.Enum.FILTER_TYPE;
import ch.yukow0.underabyss.Record.FilterRecord;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.data.worldgen.DimensionTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityEvent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

import java.util.List;
import java.util.Optional;
import java.util.Properties;

import static net.minecraft.world.level.Level.END;
import static net.minecraft.world.level.Level.NETHER;

@EventBusSubscriber
public class FilterListener {


    @SubscribeEvent
    public static void UnderY(EntityTickEvent.Pre event) {
        Entity entity = event.getEntity();
        if (entity instanceof Player player) {
            float Y = (float) player.position().get(Direction.Axis.Y);
            if (Y < 0) {
                boolean hasFilter = player.getItemBySlot(EquipmentSlot.HEAD).has(HasFilter.HAS_FILTER.get());
                if (!hasFilter) {
                    player.addEffect(new MobEffectInstance(MobEffects.WITHER, 100, 1));
                } else {
                    player.removeEffect(MobEffects.WITHER);
                }
            }
        }
    }

    @SubscribeEvent
    public static void Mining(PlayerEvent.BreakSpeed event) {
        boolean hasFilter = event.getEntity().getItemBySlot(EquipmentSlot.HEAD).has(HasFilter.HAS_FILTER.get());
        float originalSpeed = event.getOriginalSpeed();
        if (!hasFilter) {
            event.setNewSpeed(originalSpeed - 50f);
        }

    }

    @SubscribeEvent
    public static void dimensionEntering(PlayerEvent.PlayerChangedDimensionEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Player player) {
            Deal(player.level(), player);
        }

    }

    @SubscribeEvent
    public static void inDimension(EntityTickEvent.Pre event) {
        Entity entity = event.getEntity();
        if (entity instanceof Player player) {
            Deal(player.level(), player);
        }
    }

    public static void Deal(Level dimension, Player player){
        if (player.level().dimension() == NETHER) {
            if (player.getItemBySlot(EquipmentSlot.HEAD).has(HasFilter.HAS_FILTER.get())) {
                {
                    FilterRecord filterRecord = player.getItemBySlot(EquipmentSlot.HEAD).get(HasFilter.HAS_FILTER.get());
                    if (filterRecord.type() == FILTER_TYPE.BASIC || filterRecord.type() == FILTER_TYPE.ADVANCED) {
                        player.removeEffect(MobEffects.WITHER);
                        player.setRemainingFireTicks(100);
                        player.level().addParticle(ParticleTypes.FALLING_LAVA, player.getX(), player.getY(), player.getZ(), 0, 0, 0);
                    }
                    else{
                        player.setRemainingFireTicks(0);
                        player.removeEffect(MobEffects.WITHER);
                    }
                }
            }
            else{
                player.addEffect(new MobEffectInstance(MobEffects.WITHER, 100, 1));
                player.setRemainingFireTicks(100);
                player.level().addParticle(ParticleTypes.FALLING_LAVA, player.getX(), player.getY(), player.getZ(), 0, 0, 0);
            }
        }

    }

}
