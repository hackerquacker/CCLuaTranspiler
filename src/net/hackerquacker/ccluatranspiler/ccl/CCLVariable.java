package net.hackerquacker.ccluatranspiler.ccl;

public class CCLVariable{

    private final String name;
    private CCLStatement value;
    private final CCLClass parentClass;

    private String type;

    private boolean isConstant = false;
    private boolean isGlobal = false;

    public CCLVariable(CCLClass parentClass, String name){
        this.name = name;
        this.parentClass = parentClass;
    }

    public CCLVariable(CCLClass parentClass, String name, String type){
        this.name = name;
        this.type = type;
        this.parentClass = parentClass;
    }

    public String getName(){
        return this.name;
    }

    public CCLStatement getValue(){
        return this.value;
    }

    public CCLVariable setValue(CCLStatement value){
        this.value = value;
        return this;
    }

    public CCLVariable setConstant(boolean isConstant){
        this.isConstant = isConstant;
        return this;
    }

    public CCLVariable setGlobal(boolean isGlobal){
        this.isGlobal = isGlobal;
        return this;
    }

    public boolean isGlobal(){
        return this.isGlobal;
    }

    public boolean isConstant(){
        return this.isConstant;
    }

    public boolean isPartOfClass(){
        return this.parentClass != null;
    }

    public CCLClass getParentClass(){
        return this.parentClass;
    }


    @Override public String toString(){
        return "CCLVariable{" + this.name + (this.type != null ? ", type=" + this.type : "")+"}";
    }
}
