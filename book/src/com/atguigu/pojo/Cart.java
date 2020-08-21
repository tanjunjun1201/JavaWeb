package com.atguigu.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Cart {
//    private Integer totalCount;
//    private Integer totalPrice;
    private Map<Integer,CartItem> items = new LinkedHashMap<Integer,CartItem>();

    //添加商品项
    public void addItem(CartItem cartItem){
        CartItem item = items.get(cartItem.getId());
        if(item == null){
            items.put(cartItem.getId(),cartItem);
        }else{
            item.setCount(item.getCount()+1);
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }
    //删除商品项
    public void deleteItem(Integer id){
        items.remove(id);
    }

    //清空购物车
    public void clear(){
        items.clear();
    }

    //更新商品项
    public void updateCount(Integer id,Integer count){
        CartItem cartItem = items.get(id);
        if(cartItem != null){
            cartItem.setCount(count);
            cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));
        }
    }

    public Integer getTotalCount(){
        Integer totalCount = 0;
        for(Map.Entry<Integer,CartItem> entry:items.entrySet()){
            totalCount += entry.getValue().getCount();
        }
        return totalCount;
    }

    public BigDecimal getTotalPrice(){
        BigDecimal totalPrice = new BigDecimal(0);
        for(Map.Entry<Integer,CartItem> entry:items.entrySet()){
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }





    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }





    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }
}
