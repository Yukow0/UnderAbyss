package ch.yukow0.underabyss.Enum;

import ch.yukow0.underabyss.Component.HasFilter;
import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;

public enum FILTER_TYPE implements StringRepresentable {
    NONE("none"),
    BASIC("basic"),
    ADVANCED("advanced"),
    HEATRESISTANT("heat_resistant"),
    VOID("void");

    private final String name;
    // Le Codec permet à Minecraft de lire/écrire l'Enum dans le JSON et le NBT
    FILTER_TYPE(String name) { this.name = name; }
    public static final Codec<FILTER_TYPE> codec = StringRepresentable.fromEnum(FILTER_TYPE::values);

    @Override
    public String getSerializedName() { return this.name; }
}
