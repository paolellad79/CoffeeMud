package com.planet_ink.coffee_mud.Abilities.Spells;

import com.planet_ink.coffee_mud.interfaces.*;
import com.planet_ink.coffee_mud.common.*;
import com.planet_ink.coffee_mud.utils.*;
import com.planet_ink.coffee_mud.Abilities.Spells.interfaces.*;
import java.util.*;

public class Spell_InsatiableThirst extends Spell
	implements CharmDevotion, EnchantmentDevotion
{
	public Spell_InsatiableThirst()
	{
		super();
		myID=this.getClass().getName().substring(this.getClass().getName().lastIndexOf('.')+1);
		name="Insatiable Thirst";

		// what the affected mob sees when they
		// bring up their affected list.
		displayText="(Insatiable Thirst spell)";


		quality=Ability.MALICIOUS;

		canBeUninvoked=true;
		isAutoinvoked=false;

		baseEnvStats().setLevel(1);

		addQualifyingClass("Mage",1);
		addQualifyingClass("Ranger",baseEnvStats().level()+4);

		uses=Integer.MAX_VALUE;
		recoverEnvStats();
	}

	public Environmental newInstance()
	{
		return new Spell_InsatiableThirst();
	}

	public boolean invoke(MOB mob, Vector commands, Environmental givenTarget, boolean auto)
	{
		MOB target=this.getTarget(mob,commands,givenTarget);
		if(target==null) return false;

		// the invoke method for spells receives as
		// parameters the invoker, and the REMAINING
		// command line parameters, divided into words,
		// and added as String objects to a vector.
		if(!super.invoke(mob,commands,givenTarget,auto))
			return false;
		boolean success=profficiencyCheck(0,auto);

		if(success)
		{
			// it worked, so build a copy of this ability,
			// and add it to the affects list of the
			// affected MOB.  Then tell everyone else
			// what happened.
			invoker=mob;
			FullMsg msg=new FullMsg(mob,target,this,affectType,auto?"":"<S-NAME> incant(s) to <T-NAMESELF>.");
			if(mob.location().okAffect(msg))
			{
				mob.location().send(mob,msg);
				if(!msg.wasModified())
				{
					target.curState().setThirst(-150 - (mob.envStats().level() * 10));
					mob.location().show(target,null,Affect.MSG_OK_VISUAL,"<S-NAME> feel(s) incredibly thirsty!");
				}
			}
		}
		else
			return maliciousFizzle(mob,target,"<S-NAME> incant(s) to <T-NAMESELF>, but the spell fades.");
		// return whether it worked
		return success;
	}
}
