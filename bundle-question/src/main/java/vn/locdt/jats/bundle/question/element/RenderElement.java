package vn.locdt.jats.bundle.question.element;

/**
 * Created by locdt on 1/10/2018.
 */
public abstract class RenderElement {
    protected int renderHeight;

    public int getRenderHeight() {
        return this.renderHeight;
    }

    public void setRenderHeight(int renderHeight) {
        this.renderHeight = renderHeight;
    }

    public abstract void updateRenderHeight();
}
