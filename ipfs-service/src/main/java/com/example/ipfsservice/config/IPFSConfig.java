package com.example.ipfsservice.config;


import io.ipfs.api.IPFS;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class IPFSConfig {
    public IPFS ipfs;

    public IPFSConfig(){
        ipfs = new IPFS("/ip4/34.175.142.203/tcp/5001");
        //ipfs = new IPFS("/ip4/127.0.0.1/tcp/5001");
    }
}
