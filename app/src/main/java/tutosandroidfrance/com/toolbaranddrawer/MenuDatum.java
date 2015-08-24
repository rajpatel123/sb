package tutosandroidfrance.com.toolbaranddrawer;

import java.util.ArrayList;
import java.util.List;

public class MenuDatum {

private String name;
private List<Child> children = new ArrayList<Child>();
private String image;
private String column;
private String href;

/**
*
* @return
* The name
*/
public String getName() {
return name;
}

/**
*
* @param name
* The name
*/
public void setName(String name) {
this.name = name;
}

/**
*
* @return
* The children
*/
public List<Child> getChildren() {
return children;
}

/**
*
* @param children
* The children
*/
public void setChildren(List<Child> children) {
this.children = children;
}

/**
*
* @return
* The image
*/
public String getImage() {
return image;
}

/**
*
* @param image
* The image
*/
public void setImage(String image) {
this.image = image;
}

/**
*
* @return
* The column
*/
public String getColumn() {
return column;
}

/**
*
* @param column
* The column
*/
public void setColumn(String column) {
this.column = column;
}

/**
*
* @return
* The href
*/
public String getHref() {
return href;
}

/**
*
* @param href
* The href
*/
public void setHref(String href) {
this.href = href;
}

}