package br.cefetmg.games;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Goomba
{

    private Texture textura;
    private TextureRegion[][] quadrosDaAnimacao;
    Animation andarParaFrente;
//    private Sprite jogador;

    public Goomba(Texture textura)
    {
        this.textura = textura;
        quadrosDaAnimacao = TextureRegion.split(textura, 21, 24);
        andarParaFrente = new Animation(0.1f,
                quadrosDaAnimacao[0][0], // 1ª linha, 1ª coluna
                quadrosDaAnimacao[0][1], // idem, 2ª coluna
                quadrosDaAnimacao[0][2],
                quadrosDaAnimacao[0][3],
                quadrosDaAnimacao[0][4]);
        andarParaFrente.setPlayMode(PlayMode.LOOP_PINGPONG);
//        jogador = new Sprite(textura);
//        jogador.setPosition(30, 10);
    }

    void render(SpriteBatch batch, Texture[] mapLevelsTextures)
    {
        // apaga a tela, para desenharmos de novo
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // chamando o método update(), que atualiza a lógica do jogo.
        // passa para o método quanto tempo se passou desde a última vez
        // que renderizamos
        update(Gdx.graphics.getDeltaTime());

        batch.begin();
        // desenhos são realizados aqui
        batch.draw(mapLevelsTextures[0], 0, 0);
        jogador.draw(batch);
        batch.draw(mapLevelsTextures[1], 0, 0);
        batch.end();
    }

    void update(float delta)
    {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
            Gdx.app.exit();
        else if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            if (jogador.getY() + 1 < Gdx.graphics.getHeight() - jogador.getRegionHeight())
                jogador.translateY(1);
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            if (jogador.getY() - 1 > 0)
                jogador.translateY(-1);
        } else if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            if (jogador.getX() + 1 < Gdx.graphics.getWidth() - jogador.getRegionWidth())
                jogador.translateX(1);
        } else if (Gdx.input.isKeyPressed(Input.Keys.Q))
            if (jogador.getX() - 1 > 0)
                jogador.translateX(-1);
    }
}
