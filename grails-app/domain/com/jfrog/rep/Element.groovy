package com.jfrog.rep

class Element {
    String name

    static hasMany = [attributes: Attribute]
    static embedded = ['attributes']

    static constraints = {
    }

    void addAttribute(String name) {
        def attribute = new Attribute()
        attribute.name = name
        attribute.save()
        addToAttributes(attribute)
    }
}
