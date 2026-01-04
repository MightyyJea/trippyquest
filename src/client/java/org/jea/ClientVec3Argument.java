package org.jea;

import com.mojang.brigadier.Message;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.exceptions.CommandExceptionType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.commands.arguments.coordinates.Vec3Argument;
import net.minecraft.network.chat.Component;
import net.minecraft.world.phys.Vec3;

import static net.minecraft.network.chat.Component.literal;

public class ClientVec3Argument implements ArgumentType<Vec3> {
    public static SimpleCommandExceptionType NOTCOMPLETE = new SimpleCommandExceptionType(Component.literal("not complete"));
    public ClientVec3Argument(){

    }
    @Override
    public Vec3 parse(StringReader reader) throws CommandSyntaxException {
        int[] collectedInt = new int[3];
        int start = reader.getCursor();
        int bracketEven = start % 2 ==1 ? 1 : 0;
        int numberEvent = start % 2 ==1 ? 0 : 1;
        TrippyquestClient.print("start"+ reader.getString());
        while (reader.canRead())
        {
            TrippyquestClient.print(reader.peek()+" " +reader.getCursor());
            if(reader.getCursor()  % 2 == bracketEven){
                if (reader.peek() == '{' || reader.peek() == '}' || reader.peek() == ','){
                    reader.skip();
                }else {
                    throw CommandSyntaxException.BUILT_IN_EXCEPTIONS.dispatcherParseException().create("can't relsolve "+reader.peek());
                }
            }
            else if(reader.getCursor()  % 2 == numberEvent){
                collectedInt[reader.getCursor() % 3] = reader.readInt();
            }
            else
            {
                throw NOTCOMPLETE.create();
            }
        }
        return new Vec3(collectedInt[0],collectedInt[2],collectedInt[1]);
    }
}
