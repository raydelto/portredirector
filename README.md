Port redirector is a simple local port redirection application that I have made in order to perform my regular job tasks on a restricted environment.

My case was that, by company's policy I was not allowed to have admin rights on my PC, furthermore I only had access to open certain ports locally, so I decided to create this simple utility in order to redirect any port to those few ports that I was allowed to open.  So let's say I wanted to share a local MySQL connection with other peers on my LAN,  I just run the APP, it will listen to other devices on the network, each message will be redirected to local port,  message received from local port is brocasted to remote peer, so the local application will believe that those messages are sent from localhost.  

You can use this application for sharing the local Web Server bundled with Visual Studio that is aimed to be used only locally.

You can download the jar binary from the following link:

http://www.raydelto.org/new_site/bin/PortRedirector.jar
