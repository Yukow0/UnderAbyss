package ch.yukow0.underabyss.Component;

import ch.yukow0.underabyss.Record.FilterRecord;
import ch.yukow0.underabyss.Underabyss;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.lang.reflect.Array;
import java.util.logging.Filter;

public class HasFilter {
    public static final DeferredRegister.DataComponents COMPONENTS =
            DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, Underabyss.MODID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<FilterRecord>> HAS_FILTER = COMPONENTS.registerComponentType("has_filter", builder -> builder.persistent(FilterRecord.recordCodecBuilder).networkSynchronized(FilterRecord.STREAM_CODEC));
}
