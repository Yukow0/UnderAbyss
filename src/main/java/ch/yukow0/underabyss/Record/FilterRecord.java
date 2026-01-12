package ch.yukow0.underabyss.Record;

import ch.yukow0.underabyss.Enum.FILTER_TYPE;
import com.mojang.serialization.Codec;
import com.mojang.serialization.RecordBuilder;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;


public record FilterRecord(FILTER_TYPE type, int durability){
    public static final Codec<FilterRecord> recordCodecBuilder = RecordCodecBuilder.create(instance -> instance.group(FILTER_TYPE.codec.fieldOf("FilterType").forGetter(FilterRecord::type), Codec.INT.fieldOf("Durability").forGetter(FilterRecord::durability)).apply(instance, FilterRecord::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, FilterRecord> STREAM_CODEC = StreamCodec.composite(ByteBufCodecs.fromCodec(FILTER_TYPE.codec), FilterRecord::type, ByteBufCodecs.VAR_INT, FilterRecord::durability, FilterRecord::new);
}
