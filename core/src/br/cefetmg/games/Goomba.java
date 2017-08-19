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
import com.badlogic.gdx.math.Vector2;

public class Goomba
{

    private Texture textura;
    private TextureRegion[][] quadrosDaAnimacao;
    Animation animacaoAtual, andarParaFrente, andarParaDireita, andarParaTras, andarParaEsquerda;
    float tempoDaAnimacao = 0;
    private Vector2 position;

    private int larguraDoQuadro = 21;
    private int alturaDoQuadro = 24;
    private int positionX;
    private int positionY;
//    private Sprite jogador;

    public Goomba(Texture textura)
    {
        this.textura = textura;
        quadrosDaAnimacao = TextureRegion.split(textura, larguraDoQuadro, alturaDoQuadro);
        andarParaFrente = new Animation(0.1f,
                quadrosDaAnimacao[0][0],
                quadrosDaAnimacao[0][1],
                quadrosDaAnimacao[0][2],
                quadrosDaAnimacao[0][3],
                quadrosDaAnimacao[0][4]);
        andarParaDireita = new Animation(0.1f,
                quadrosDaAnimacao[1][0],
                quadrosDaAnimacao[1][1],
                quadrosDaAnimacao[1][2],
                quadrosDaAnimacao[1][3],
                quadrosDaAnimacao[1][4]);
        andarParaTras = new Animation(0.1f,
                quadrosDaAnimacao[2][0],
                quadrosDaAnimacao[2][1],
                quadrosDaAnimacao[2][2],
                quadrosDaAnimacao[2][3],
                quadrosDaAnimacao[2][4]);
        andarParaEsquerda = new Animation(0.1f,
                quadrosDaAnimacao[3][0],
                quadrosDaAnimacao[3][1],
                quadrosDaAnimacao[3][2],
                quadrosDaAnimacao[3][3],
                quadrosDaAnimacao[3][4]);
        andarParaFrente.setPlayMode(PlayMode.LOOP_PINGPONG);
        animacaoAtual = andarParaFrente;
        tempoDaAnimacao = 0;

        positionX = 30;
        positionY = 10;
        position = new Vector2(positionX, positionY);
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
//        jogador.draw(batch);
        batch.draw((TextureRegion) animacaoAtual.getKeyFrame(tempoDaAnimacao), position.x, position.y);
        batch.draw(mapLevelsTextures[1], 0, 0);
        batch.end();
    }

    void update(float delta)
    {
        tempoDaAnimacao += Gdx.graphics.getDeltaTime();

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
            Gdx.app.exit();
        //Andar para cima
        else if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            animacaoAtual = andarParaTras;
            if (position.y + 1 < Gdx.graphics.getHeight() - alturaDoQuadro)
                position.add(0, 1);
            //Andar para baixo
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            animacaoAtual = andarParaFrente;
            if (position.y - 1 > 0)
                position.add(0, -1);
            //Andar para direita
        } else if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            animacaoAtual = andarParaDireita;
            if (position.x + 1 < Gdx.graphics.getWidth() - larguraDoQuadro)
                position.add(1, 0);
            //Andar para esquerda
        } else if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            animacaoAtual = andarParaEsquerda;
            if (position.x - 1 > 0)
                position.add(-1, 0);
        }
//        else if (Gdx.input.isKeyPressed(Input.Keys.W)) {
//            if (jogador.getY() + 1 < Gdx.graphics.getHeight() - jogador.getRegionHeight())
//                jogador.translateY(1);
//        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
//            if (jogador.getY() - 1 > 0)
//                jogador.translateY(-1);
//        } else if (Gdx.input.isKeyPressed(Input.Keys.E)) {
//            if (jogador.getX() + 1 < Gdx.graphics.getWidth() - jogador.getRegionWidth())
//                jogador.translateX(1);
//        } else if (Gdx.input.isKeyPressed(Input.Keys.Q))
//            if (jogador.getX() - 1 > 0)
//                jogador.translateX(-1);
    }
}
