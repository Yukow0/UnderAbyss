package ch.yukow0.underabyss.Crafts;

import ch.yukow0.underabyss.Component.HasFilter;
import ch.yukow0.underabyss.Enum.FILTER_TYPE;
import ch.yukow0.underabyss.Items.Filter.AdvancedFilter;
import ch.yukow0.underabyss.Items.Filter.BasicFilter;
import ch.yukow0.underabyss.Record.FilterRecord;
import ch.yukow0.underabyss.Serializer.FilterSerializer;
import ch.yukow0.underabyss.Tags.AirFilters;
import net.minecraft.ChatFormatting;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.ItemLore;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static net.minecraft.world.item.ItemStack.EMPTY;

public class FilterHelmet extends CustomRecipe {
    public FilterHelmet(CraftingBookCategory category) {
        super(category);
    }

    @Override
    public boolean matches(CraftingInput input, @NotNull Level level) {
        ItemStack helmet = EMPTY;
        boolean foundFilter = false;
        int totalItems = 0;

        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getItem(i);
            if (stack.isEmpty()) continue;

            totalItems++;


            if (stack.is(AirFilters.AIR_FILTERS)) {
                foundFilter = true;
            }

            else if (stack.is(ItemTags.HEAD_ARMOR) && getArmorDefense(stack) >= 2) {
                helmet = stack;
            }
        }
        if (helmet.has(HasFilter.HAS_FILTER.get())){
            return false;
        }


        return !helmet.isEmpty() && foundFilter && totalItems == 2;
    }

    @Override
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider provider) {
        ItemStack helmet = EMPTY;
        ItemStack filter = EMPTY;


        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getItem(i);
            if (!stack.isEmpty() && stack.is(ItemTags.HEAD_ARMOR)) {
                helmet = stack;
                break;
            }
            if (stack.is(AirFilters.AIR_FILTERS)) {
                filter = stack;
            }
        }

        if (helmet.isEmpty() || filter.isEmpty()) return EMPTY;


        ItemStack result = helmet.copy();
        result.setCount(1);


        Map<Item, FILTER_TYPE> map = Map.of(BasicFilter.BASIC_FILTER.get(), FILTER_TYPE.BASIC, AdvancedFilter.ADVANCED_FILTER.get(), FILTER_TYPE.ADVANCED);
        FILTER_TYPE type = map.getOrDefault(filter.getItem(), FILTER_TYPE.BASIC);
        switch (type){
            case BASIC:
                break;
                case ADVANCED:
                    break;
        }
        result.set(HasFilter.HAS_FILTER.get(), new FilterRecord(FILTER_TYPE.BASIC, 1000));
        result.set(DataComponents.LORE, new ItemLore(List.of(
                Component.translatable("tooltip.underabyss.helmet_filter.description").withStyle(ChatFormatting.BLUE),
                Component.literal("Durability: 1000").withStyle(ChatFormatting.AQUA)
        )));


        return result;
    }


    private double getArmorDefense(ItemStack stack) {
        double defense = 0;

        ItemAttributeModifiers modifiers = stack.getOrDefault(DataComponents.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.EMPTY);

        for (ItemAttributeModifiers.Entry entry : modifiers.modifiers()) {

            if (entry.attribute().is(Attributes.ARMOR) && entry.slot().equals(EquipmentSlotGroup.HEAD)) {
                defense += entry.modifier().amount();
            }
        }
        return defense;
    }


    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public RecipeSerializer<? extends CustomRecipe> getSerializer() {
        return FilterSerializer.FILTER_HELMET_SERIALIZER.get();
    }
}