package session_seven_topics.assignment_problems;

public class GalleryDescriptionCards {

    public static abstract class ArtPiece {
        private static int counter = 1000;
        private final String pieceId;
        protected final String title;

        public ArtPiece(String title) {
            if (title == null || title.trim().isEmpty()) {
                throw new IllegalArgumentException("Title cannot be blank");
            }
            this.title = title.trim();
            this.pieceId = "ART-" + (++counter);
        }

        public String getPieceId() {
            return this.pieceId;
        }

        public String getTitle() {
            return this.title;
        }

        public abstract String describe();
    }

    public static class Painting extends ArtPiece {
        public Painting(String title) {
            super(title);
        }

        @Override
        public String describe() {
            return "Painting: " + this.title + ", framed on canvas";
        }
    }

    public static class Sculpture extends ArtPiece {
        public Sculpture(String title) {
            super(title);
        }

        @Override
        public String describe() {
            return "Sculpture: " + this.title + ", carved from stone";
        }
    }

    public static void main(String[] args) {
        Painting p = new Painting("Sunset Fields");
        System.out.println(p.describe());
        System.out.println("Painting ID: " + p.getPieceId());

        Sculpture s = new Sculpture("The Thinker II");
        System.out.println(s.describe());
        System.out.println("Sculpture ID: " + s.getPieceId());
    }
}
