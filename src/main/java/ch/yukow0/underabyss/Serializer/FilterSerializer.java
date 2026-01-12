package ch.yukow0.underabyss.Serializer;

import ch.yukow0.underabyss.Crafts.FilterHelmet;
import ch.yukow0.underabyss.Underabyss;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.gui.screens.recipebook.CraftingRecipeBookComponent;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.crafting.*;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;

public class FilterSerializer {
    public static final DeferredRegister<@NotNull RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, Underabyss.MODID);

    public static final DeferredHolder<@NotNull RecipeSerializer<?>, @NotNull RecipeSerializer<FilterHelmet>> FILTER_HELMET_SERIALIZER =
            SERIALIZERS.register("filter_helmet", () -> new CustomRecipe.Serializer<>(FilterHelmet::new));
}
