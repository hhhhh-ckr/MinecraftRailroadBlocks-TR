package io.github.samthegamer39.railroadblocksaddon.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PoleBlock extends Block implements SimpleWaterloggedBlock {

    //Controls the shape of the collision/render block.
    private static final VoxelShape SHAPE = Block.box(7, 0, 7, 9, 16, 9);
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public PoleBlock(Properties properties) {
        super(properties); //What is super()? Why does this break EVERYTHING?
        this.registerDefaultState(this.defaultBlockState()
                .setValue(WATERLOGGED, false));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState()
                .setValue(WATERLOGGED, context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER); //.getOpposite() can be added to flip the model.
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(WATERLOGGED);
    }

    @SuppressWarnings("deprecation") //Why is getFluidState deprecated? Is there a better alternative?
    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @SuppressWarnings("deprecation") //Why is getShape() deprecated?
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @SuppressWarnings("deprecation") //Why is updateShape deprecated? Is there a better alternative?
    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos position, BlockPos neighborPos){
        if (state.getValue(WATERLOGGED)) {
            world.scheduleTick(position,Fluids.WATER, Fluids.WATER.getTickDelay(world));
        }

        return super.updateShape(state, direction, neighborState, world, position, neighborPos);
    }
}
