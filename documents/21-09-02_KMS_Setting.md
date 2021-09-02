# KMS Setting

# Install Docker

[Install Docker Engine on Ubuntu](https://docs.docker.com/engine/install/ubuntu/)

- sudo apt-get remove docker docker-engine docker.io containerd runc
- sudo apt-get update
- sudo apt-get install \
    apt-transport-https \
    ca-certificates \
    curl \
    gnupg \
    lsb-release
- curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /usr/share/keyrings/docker-archive-keyring.gpg
- echo \
  "deb [arch=amd64 signed-by=/usr/share/keyrings/docker-archive-keyring.gpg] https://download.docker.com/linux/ubuntu \
  $(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
- sudo apt-get update
- sudo apt-get install docker-ce docker-ce-cli containerd.io
- sudo docker run hello-world
- sudo chmod 666 /var/run/docker.sock

# Install Kurento

- docker pull kurento/kurento-media-server:latest
- docker run -d --name kms --network host kurento/kurento-media-server:latest

# STUN/TURN Settings

- sudo apt-get update && sudo apt-get install --no-install-recommends --yes coturn
- sudo vim /etc/default/coturn
    - TURNSERVER_ENABLED=1
- sudo vim /etc/turnserver.conf
    - listening-port=3478
    tls-listening-port=5349
    listening-ip={private IP}
    external-ip={public IP}/{private IP}
    relay-ip={public IP}
    fingerprint
    lt-cred-mech
    user=myuser:mypassword
    realm=myrealm
    log-file=/var/log/turn.log
    simple-log
- sudo service coturn restart

# Kurento STUN/TURN Settings

- docker ps -a
- docker exec -it {containerId} /bin/bash
- apt-get update && apt-get install vim
- vim /etc/kurento/modules/kurento/WebRtcEndpoint.conf.ini
    - stunServerAddress={public IP}
    stunServerPort=3478
    turnURL=myuser:mypassword@{public IP}?transport=udp