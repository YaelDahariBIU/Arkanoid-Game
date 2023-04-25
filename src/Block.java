import biuoop.DrawSurface;

public class Block implements Collidable, Sprite {
    private Rectangle rect;
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }
    public Block(Rectangle rect) {
        this.rect = rect;
    }
    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        boolean collidedVer = false, collidedHor = false;
        double rectX = this.rect.getUpperLeft().getX();
        double rectY = this.rect.getUpperLeft().getY();
        double height = this.rect.getHeight();
        double width = this.rect.getWidth();
        if (Point.doubleEquals(collisionPoint.getX(), rectX + width)
            || Point.doubleEquals(collisionPoint.getX(), rectX)) {
            collidedVer = true;
        }
        if (Point.doubleEquals(collisionPoint.getY(), rectY + height)
            || Point.doubleEquals(collisionPoint.getY(), rectY)) {
            collidedHor = true;
        }
        if (collidedVer) {
            currentVelocity.setDx(-currentVelocity.getDx());
        }
        if (collidedHor) {
            currentVelocity.setDy(-currentVelocity.getDy());
        }
        return currentVelocity;
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.fillRectangle((int) rect.getUpperLeft().getX(),
                (int) rect.getUpperLeft().getY(), (int) rect.getWidth(),
                (int) rect.getHeight());
    }

    @Override
    public void timePassed() {
        return;
    }

    @Override
    public void addToGame(Game game) {
        game.addCollidable(this);
        game.addSprite(this);
    }

}
