package istia.ei4.ProjetISTIA;

/**
 * Created by Pierre on 21/01/2015.
 */
public interface IGameObject {

    public void create();
    public void load(RenderManager renderManager);
    public void draw(RenderManager renderManager);
    public void update(GameManager gameManager);
    public void destroy();
}
