![](https://github.com/GhastCraftHD/Holography/blob/master/documentation/about_white.png?raw=true)

**Holography** is a tool that makes working with Text Displays much easier.

Text Displays were added in **Minecraft 1.19.4** and are an easy and performant way to **display text** in your Minecraft worlds. Some of their biggest advantages are that you **can change their size and rotation** and change **multiple text based settings** like Text Alignment, or the line width.

Text Displays are not the only type of Display entities that were added in 1.19.4. For the other ones I have created **separate plugins:**
* Block Displays: [Miniaturise](https://hangar.papermc.io/GhastCraftHD/Miniaturise)
* Item Displays: [Showcase](https://hangar.papermc.io/GhastCraftHD/Showcase)

<img align="right" src="https://github.com/GhastCraftHD/Holography/blob/master/documentation/installation_white.png?raw=true">

<p align="center">
<img src="https://github.com/GhastCraftHD/Holography/blob/master/documentation/installation_guide_white.png?raw=true">
  </p>
  
  Please notice the plugins [license](https://github.com/GhastCraftHD/Holography/blob/master/LICENSE)
  
  ![](https://github.com/GhastCraftHD/Holography/blob/master/documentation/config_white.png?raw=true)
  
  You can alter the default configuration by changing the values of the `config.yml` file in the plugins data folder.
  
 The billboard setting states the default billboard a newly spawned Text Display will have. Other options are `FIXED`, `HORIZONTAL` and `VERTICAL`. The billboard of a Text Display determines how the text will **rotate to the player**. `CENTER` is how Minecraft usually shows text like player names. With the other billboards you can **lock this rotation** on either **one or both axes**.
 
 The `radius` setting determines the range of the /holo list command that you need for selecting any already spawned Text Displays. **Note that higher values might hurt the servers and clients performance.**
 
 ```yaml
 check-for-update: true
billboard: CENTER
radius: 15
tool: ECHO_SHARD
 ```
 
 <img align="right" src="https://github.com/GhastCraftHD/Holography/blob/master/documentation/permissions_white.png?raw=true">\
\
By default only server operators are able to use Holography. If you want to give other players the right to use Holography you need to grant them the permission `holography.use`

![](https://github.com/GhastCraftHD/Holography/blob/master/documentation/tutorial_white.png?raw=true)

To spawn a new Text Display simply use the `/holo create <text>` command. If you want to use custom colors, text decorations etc, you can use the MiniMessage format. You can find their documentation here. They also developed an amazing web viewer where you can test and create more complex texts.

To adjust the position, size or rotation of your Text Display you can use the adjuster tool (echo shard by default) together with the adjuster interface. You can open the adjuster interface by holding the adjuster tool in your hand, sneak and right click or by using `/holo edit`.

The configuration that you select in the adjuster interface will be bound to the adjuster tool.

To select an already existing Text Display, stand near it and use `/holo list`. After that you can click on the text of the display in the chat to select it.

To remove a Text Display simply click the barrier in the adjuster interface or use `/holo remove`.

<img align="right" src="https://github.com/GhastCraftHD/Holography/blob/master/documentation/support_white.png?raw=true">\
\
If you run into any issues feel free to report them to me on [GitHub](https://github.com/GhastCraftHD/Holography/issues). There you can also suggest new features that could be added to Holography.

<p align="center">
<a href="https://github.com/GhastCraftHD/Holography/issues">
<img src="https://github.com/GhastCraftHD/Miniaturise/blob/master/documentation/github_button.png?raw=true">
</a></p>

![](https://github.com/GhastCraftHD/Holography/blob/master/documentation/gallery_white.png?raw=true)

![](https://github.com/GhastCraftHD/Holography/blob/master/showoff.png?raw=true)

![](https://github.com/GhastCraftHD/Holography/blob/master/holography.png?raw=true)
