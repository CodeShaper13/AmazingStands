package com.codeshaper.amazingstands;

import java.lang.reflect.Method;
import java.util.List;

import com.codeshaper.amazingstands.capabilities.AmazingStandDataProvider;
import com.codeshaper.amazingstands.capabilities.IAmazingStandData;
import com.codeshaper.amazingstands.packets.PacketBasePlateStack;
import com.codeshaper.amazingstands.packets.PacketModelId;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

//net.minecraftforge.common.ForgeHooks
public class AmazingStandEventHandler {
	
	private final Method methodSetShowArms;
	private final Method methodSetSmall;
		
	public AmazingStandEventHandler() {
		this.methodSetShowArms = ReflectionHelper.findMethod(EntityArmorStand.class, "setShowArms", "TODO", boolean.class);
		this.methodSetSmall = ReflectionHelper.findMethod(EntityArmorStand.class, "setSmall", "TODO", boolean.class);
	}
	
	@SubscribeEvent
	public void attackEntity(AttackEntityEvent event) {	
		if(event.getTarget() instanceof EntityArmorStand) {
			EntityPlayer player = event.getEntityPlayer();			
			if(player.getHeldItemMainhand() != null && player.isSneaking()) {				
				EntityArmorStand armorStand = (EntityArmorStand) event.getTarget();
				ItemStack heldStack = player.getHeldItemMainhand();
				Item heldItem = heldStack.getItem();
				InventoryPlayer inventory = player.inventory;
				int index = inventory.currentItem;
				
				IAmazingStandData cap = armorStand.getCapability(AmazingStandDataProvider.CAPABILITY, null);
				
				boolean somethingHappened = false;
				
				// Remove base plate.
				if(heldItem instanceof ItemPickaxe) {
					ItemStack standStack = cap.getStandStack();
					if(!standStack.isEmpty()) {
						Block.spawnAsEntity(armorStand.world, (new BlockPos(armorStand)), standStack);
						cap.setStandStack(ItemStack.EMPTY);
						somethingHappened = true;
					}
				}				
				// Toggle size.
				else if(heldItem instanceof ItemAxe) {
					this.armorStandSetSmall(armorStand, !armorStand.isSmall());
			        somethingHappened = true;
				}				
				// Add/remove arms.
				else if(heldItem == Items.STICK) {			        					
			        if (!armorStand.getShowArms() && heldStack.getCount() >= 2) {
			        	this.removeItems(player, 2);
			        	this.armorStandSetShowArms(armorStand, true);
			        	this.playSoud(armorStand);
			        } else if (armorStand.getShowArms()) {
			        	int overflowSticks = 0;
			        	for(int i = 0; i < 2; i++) {
			        		if(!inventory.addItemStackToInventory(new ItemStack(Items.STICK, 1))) {
			        			overflowSticks = 1;
			        		}
			        	}
			        	if(overflowSticks > 0) {
			        		Block.spawnAsEntity(armorStand.world, new BlockPos(armorStand).up(), new ItemStack(Items.STICK, overflowSticks));
			        	}
			        	
			        	this.playSoud(armorStand);
			        
			        	// Drops held items, if there are any.
			            if(!player.capabilities.isCreativeMode) {
				        	if (!armorStand.getHeldItemMainhand().isEmpty()) {
				        		Block.spawnAsEntity(armorStand.world, new BlockPos(armorStand).up(), armorStand.getHeldItemMainhand());
				        		armorStand.setHeldItem(EnumHand.MAIN_HAND, ItemStack.EMPTY);
				        	}
				        	if (!armorStand.getHeldItemOffhand().isEmpty()) {
				        		Block.spawnAsEntity(armorStand.world, new BlockPos(armorStand).up(), armorStand.getHeldItemOffhand());
				        		armorStand.setHeldItem(EnumHand.OFF_HAND, ItemStack.EMPTY);
				        	}
			            }
			        		
			        	this.armorStandSetShowArms(armorStand, false);
			        }
			        somethingHappened = true;
				}				
				// Place banner on head.
				else if(armorStand.getItemStackFromSlot(EntityEquipmentSlot.HEAD).isEmpty() && heldItem != Items.SKULL &&
						(heldItem.isValidArmor(heldStack, EntityEquipmentSlot.HEAD, armorStand) || heldItem == Items.BANNER)) {
					
					this.removeItems(player, 1);
					ItemStack s = player.getHeldItem(EnumHand.MAIN_HAND);
					armorStand.setItemStackToSlot(EntityEquipmentSlot.HEAD, s.splitStack(1));					
					somethingHappened = true;
				}
				// Set model.
				else if(heldItem == Items.SKULL) {
					int i = heldStack.getMetadata() + 1;
					if(i != cap.getModelId()) {
						cap.setModelId(i);
						PacketManager.INSTANCE.sendToDimension(new PacketModelId(i, cap.getModelId()), armorStand.dimension);
						this.removeItems(player, 1);
						somethingHappened = true;
					}
				}				
				// Gravity.
				else if(heldItem == Items.SLIME_BALL) {
					if(!armorStand.hasNoGravity()) {
						armorStand.setNoGravity(true);
						this.removeItems(player, 1);
						somethingHappened = true;
					}
				}				
				// Remove no gravity and/or invisibility.
				else if(heldItem == Items.MILK_BUCKET) {
					boolean consumedMilk = false;
					if(armorStand.hasNoGravity()) {
						armorStand.setNoGravity(false);
						consumedMilk = true;
					}
					if(armorStand.isInvisible()) {
						armorStand.setInvisible(false);
						consumedMilk = true;
					}
					
					if(consumedMilk) {
						inventory.setInventorySlotContents(index, new ItemStack(Items.BUCKET));
						somethingHappened = true;
					}
				}					
				// Change slab.
				else if(heldItem instanceof ItemSlab && cap.getStandStack() == ItemStack.EMPTY) {
					this.removeItems(player, 1);
					cap.setStandStack(player.getHeldItem(EnumHand.MAIN_HAND).splitStack(1));
					PacketManager.INSTANCE.sendToDimension(new PacketBasePlateStack(i, cap.getStandStack()), armorStand.dimension);
					somethingHappened = true;
				}
				// Make invisible.
				else if(heldItem == Items.POTIONITEM) {
					PotionUtils.getEffectsFromStack(heldStack);
		            List<PotionEffect> list = PotionUtils.getEffectsFromStack(heldStack);
		            for(PotionEffect effect : list) {
		            	if(effect.getPotion() == MobEffects.INVISIBILITY) {
	                    	this.removeItems(player, 1);
							inventory.setInventorySlotContents(index, new ItemStack(Items.GLASS_BOTTLE));
	                    	armorStand.setInvisible(true);
	                    	somethingHappened = true;
	                    	break;
	                    }
		            }
				}
				
				event.setCanceled(true);
			}	
		}
	}
	
	@SubscribeEvent
	public void startTracking(PlayerEvent.StartTracking event) {
		Entity e = event.getTarget();
		if(e instanceof EntityArmorStand) {
			IAmazingStandData cap = e.getCapability(AmazingStandDataProvider.CAPABILITY, null);
			
			int i = e.getEntityId();
			PacketManager.INSTANCE.sendToDimension(new PacketModelId(i, cap.getModelId()), e.dimension);			
			PacketManager.INSTANCE.sendToDimension(new PacketBasePlateStack(i, cap.getStandStack()), e.dimension);
			
			System.out.println(cap.getModelId());
			System.out.println(cap.getStandStack().toString());
		}
	}
	
	@SubscribeEvent
	public void attachCapability(AttachCapabilitiesEvent<Entity> event) {
		if(event.getObject() instanceof EntityArmorStand) {	
			event.addCapability(new ResourceLocation(AmazingStands.MOD_ID, "armorStandData"), new AmazingStandDataProvider());
		}
	}
	
	@SubscribeEvent
	public void livingDeath(LivingDeathEvent event) {
		if(event.getEntity() instanceof EntityArmorStand) {
			EntityArmorStand e = (EntityArmorStand) event.getEntity();
			Block.spawnAsEntity(e.world, new BlockPos(e), e.getCapability(AmazingStandDataProvider.CAPABILITY, null).getStandStack());
		}
	}
	
	// Copied from ArmorStand.playBrokenSound
	private void playSoud(EntityArmorStand armorStand) {
        armorStand.world.playSound((EntityPlayer)null, armorStand.posX, armorStand.posY, armorStand.posZ, SoundEvents.ENTITY_ARMORSTAND_BREAK, armorStand.getSoundCategory(), 1.0F, 1.0F);
	}
	
	private void armorStandSetShowArms(EntityArmorStand armorStand, boolean flag) {
		try {
			this.methodSetShowArms.invoke(armorStand, flag);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void armorStandSetSmall(EntityArmorStand armorStand, boolean flag) {
		try {
			this.methodSetSmall.invoke(armorStand, flag);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void removeItems(EntityPlayer player, int count) {
        ItemStack itemstack = player.getHeldItem(EnumHand.MAIN_HAND);
		if (!player.capabilities.isCreativeMode) {
            itemstack.shrink(count);
        }
	}
}
