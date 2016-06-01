/*
 * Ant Game
 * By Jack Woods
 * 
 *----Slick2D License----
 *
 *Copyright (c) 2013, Slick2D
 *
 *All rights reserved.
 *
 *Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name of the Slick2D nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS “AS IS” AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *
 *----LWJGL License (Lightweight Java Game Library)----
 * 
 *
 *Copyright © 2012-2016 Lightweight Java Game Library Project
 *All rights reserved.
 *
 *Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 *    Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *    Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 *    Neither the name of 'Light Weight Java Game Library' nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission. 
 *
 *THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *These Licenses are distributed as a text document, along with the binary executable.
 */


import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;

import org.lwjgl.LWJGLUtil;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends StateBasedGame{
	private static int width;
	private static int height;
	Handler handler;

	public Game(String title) {
		super(title);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //get the resolution of the screen
		
		//initialize resolution variables
		width = (int) screenSize.getWidth();
		height = (int) screenSize.getHeight();
	}

	public static void main(String[] args) throws SlickException {
		//sets platform-dependent native libraries for LWJGL and OpenGL (Slick2D uses these as well)
		File JGLLib = new File("./lib/native/windows/");
		switch(LWJGLUtil.getPlatform())
		{
		    case LWJGLUtil.PLATFORM_WINDOWS:
		    {
		        JGLLib = new File("./lib/native/windows/");
		    }
		    break;

		    case LWJGLUtil.PLATFORM_LINUX:
		    {
		        JGLLib = new File("./lib/native/linux/");
		    }
		    break;

		    case LWJGLUtil.PLATFORM_MACOSX:
		    {
		        JGLLib = new File("./lib/native/macosx/");
		    }
		    break;
		}
		System.setProperty("org.lwjgl.librarypath", JGLLib.getAbsolutePath());
		
		//Start the game
		AppGameContainer app = new AppGameContainer(new Game("Ants"));
		app.setDisplayMode(width, height, true);
		app.setTargetFrameRate(60);
		app.start();
	}

	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {
		//initialize handler
		handler = new Handler(width, height, getContainer());
		
		// Add the various game states, in order          ID
		this.addState(new GameMenuState(width, height)); //0 - choose gamemode, join/create LAN games, etc
		this.addState(new GameplayStateAntHill(width, height)); //1 - In-Game state where the player views a cross-section of the ant hill
		this.addState(new GameplayStateMap(width, height)); //2 - In-Game state where the player can see the game's map from a top-down perspective
		this.addState(new GameOverState(width, height)); //2  - Post-game state where the player is presented with statistice recorded during the game + victory/defeat screen
		
	}

}
