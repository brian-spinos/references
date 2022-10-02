# OSI Model

![](https://techdifferences.com/wp-content/uploads/2016/03/Untitled-1.jpg)

###### What is it? 
```
It is a standard for network comunication
```

https://www.lifewire.com/open-systems-interconnection-model-816290

http://searchnetworking.techtarget.com/definition/OSI

- In a given message between users, there will be a flow of data down through the layers in the source computer, across the network and then up through the layers in the receiving computer

- OSI (Open Systems Interconnection) is reference model for how applications can communicate over a network.

- OSI is rarely actually implemented, as few network products or standard tools keep all related functions together in well-defined layers as related to the model.

- The TCP/IP protocols, which define the Internet, do not map cleanly to the OSI model. see this link: https://www.thegeekstuff.com/2011/11/tcp-ip-fundamentals/

###### The Seven Layers of the OSI Model

> The bottom three layers of the OSI Model are referred to as the Media Layers, while the top four layers are the Host layers.
> The layers are numbered from 1 through 7 beginning at the bottom. The layers are:

`Application layer (layer 7)` - Network Process to Application. This end-user layer packages the data received from the
Presentation Layer in the format needed by the application or end-user process that receives it. Examples include browsers,
SMTP, HTTP, and FTP. This layer also creates what is to be sent back to the Presentation Layer.

`The presentation layer (layer 6)` - Data Representation and Encryption, including format conversions. Think of this layer as
the translator. Examples include ASCII, TIFF, JPEG, MIDI, and MPEG.

`Session layer (layer 5)` - Interhost Communication. This layer manages multiple types of communications and sends data to
logical ports, including those using NFS and SQL.

`Transport layer (layer 4)` - End-to-End Connections and Reliability. As the name implies, this layer moves data across
network connections, usually using TCP. It also handles error recovery and re-transmissions.

`Network layer (layer 3)` - Path Determination, IP, and Routing. Layer 3 formats data as packets. Directs the data to the
correct physical path.

`Data Link layer (layer 2)` - This is the most complex layer in the OSI model, and it is sometimes divided into two parts: one
for media access control and one for logical link control.

`The physical layer (layer 1)` - Media, Signal and Binary Transmission. Examples include hubs, repeaters, and Ethernet cables.
Data is transmitted by an electric voltage, radio frequencies, infrared or ordinary light.

###### Having trouble remembering the layer order? Just keep the phrase:

> "All People Seem To Need Data Processing" in mind.
