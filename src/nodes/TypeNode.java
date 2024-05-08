package nodes;


public class TypeNode extends DefineNode {

        public TypeNode(){}
        private String typeName;

        public TypeNode(String type) {
                this.typeName = type;
        }

        public String getTypeName() {
                return typeName;
        }
        public void setTypeName(String typeName) {
                this.typeName = typeName;
        }
}
