package com.planet_ink.coffee_mud.Abilities.Songs;

import com.planet_ink.coffee_mud.interfaces.*;
import com.planet_ink.coffee_mud.common.*;
import com.planet_ink.coffee_mud.utils.*;
import java.util.*;


public class Song_Flying extends Song
{

	public Song_Flying()
	{
		super();
		myID=this.getClass().getName().substring(this.getClass().getName().lastIndexOf('.')+1);
		name="Flying";
		displayText="(Song of Flying)";
		miscText="";

		canBeUninvoked=true;
		isAutoinvoked=false;

		baseEnvStats().setLevel(23);
		quality=Ability.BENEFICIAL_OTHERS;

		addQualifyingClass("Bard",23);

		baseEnvStats().setAbility(0);
		uses=Integer.MAX_VALUE;
		recoverEnvStats();
	}

	public Environmental newInstance()
	{
		return new Song_Flying();
	}
	public void affectEnvStats(Environmental affected, EnvStats affectableStats)
	{
		super.affectEnvStats(affected,affectableStats);
		if(invoker==null) return;

		affectableStats.setDisposition(affectableStats.disposition()|Sense.IS_FLYING);
	}
}
