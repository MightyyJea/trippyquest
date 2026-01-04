package org.jea;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.network.chat.Component;
import net.minecraft.world.phys.Vec3;

public class ClientVec3Argument implements ArgumentType<Vec3> {
    public static SimpleCommandExceptionType NOTCOMPLETE = new SimpleCommandExceptionType(Component.literal("not complete"));
    public ClientVec3Argument(){

    }
    @Override
    public Vec3 parse(StringReader reader) throws CommandSyntaxException {try {
			// This requires the argument to be surrounded by quotation marks.
			// eg: "{1, 2, 3}"
			String string = reader.readString();

			string = string.replace("{", "").replace("}", "");
			// Remove the { and } from the string using regex.

			// Split the string into the x, y, and z values.
			String[] split = string.split(",");

			// Parse the x, y, and z values from the split string.
            double x = Double.parseDouble(split[0].trim());
            double y = Double.parseDouble(split[1].trim());
            double z = Double.parseDouble(split[2].trim());

			// Return the BlockPos.
			return new Vec3(x, y, z);
		} catch (Exception e) {
			// Throw an exception if anything fails inside the try block.
			throw CommandSyntaxException.BUILT_IN_EXCEPTIONS.dispatcherParseException().create("Invalid BlockPos format. Expected {x, y, z}");
		}}
}
