# ISS Tracking with Diffusion

Tracks the International Space Station in real-time by publishing its position to a Diffusion server. Two clients subscribe to the topics on the Diffusion server. A Java client logs updates to the console, and a JavaScript client plots the position on an OpenLayers map.

## Setup
1. **Build the project**: Run `mvn clean install` in the root directory.
2. **Start Diffusion**: Either start a local Diffusion server, or replace the default server URLs with URLs for a running Diffusion cloud instance. (See https://docs.diffusiondata.com/docs/quickstartguide/onprem/index-on-prem.html for info on getting started with Diffusion.)
3. **Run Java publisher and logging client**: This creates the topics on the server, and creates a client session which subscribes to receive updates and logs new updates to the console.
   ```sh
   java -jar target/ISS-Demo-1.0-SNAPSHOT-jar-with-dependencies.jar
   ```
   
4. **Run Web Client**: Open `index.html` or serve locally: (note that the Diffusion server runs on 8080 by default)
   ```sh
   npx http-server web-client -p 8085
   ```