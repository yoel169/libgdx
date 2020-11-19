package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;

public class B2DContactListener implements ContactListener {

    private B2DModel parent;

    public B2DContactListener(B2DModel parent){
        this.parent = parent;
    }

    private void shootUpInAir(Fixture staticFixture, Fixture otherFixture){
        System.out.println("Adding Force");
        otherFixture.getBody().applyForceToCenter(new Vector2(-100000,-100000), true);
    }


    @Override
    public void beginContact(Contact contact) {
        System.out.println("Contact");
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();
        System.out.println(fa.getBody().getType()+" has hit "+ fb.getBody().getType());

        //sea
        if(fa.getBody().getUserData() == "IAMTHESEA"){
            System.out.println("Swimming");
            parent.isSwimming = true;
            return;
        }else if(fb.getBody().getUserData() == "IAMTHESEA"){
            System.out.println("Swimming");
            parent.isSwimming = true;
            return;
        }

        //check of either bodies are hitting the floor
        if(fa.getBody().getType() == BodyDef.BodyType.StaticBody){
            this.shootUpInAir(fa, fb);
        }else if(fb.getBody().getType() == BodyDef.BodyType.StaticBody){
            this.shootUpInAir(fb, fa);
        }else{
            // neither a nor b are static so do nothing
        }
    }

    @Override
    public void endContact(Contact contact) {
        System.out.println("Contact");
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

        //end swimming
        if(fa.getBody().getUserData() == "IAMTHESEA"){
            parent.isSwimming = false;
            System.out.println("out of water");
            return;
        }else if(fb.getBody().getUserData() == "IAMTHESEA"){
            System.out.println("out of water");
            parent.isSwimming = false;
            return;
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
    }

}
