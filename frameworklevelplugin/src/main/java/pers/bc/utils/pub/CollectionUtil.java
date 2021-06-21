package pers.bc.utils.pub;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import pers.bc.utils.Bean.BeanHelper;
import pers.bc.utils.Bean.ReflectionUtilbc;
import pers.bc.utils.cons.ObjectHandler;
import pers.bc.utils.cons.ObjectProcess;
import pers.bc.utils.cons.PubConsUtilbc;

import com.google.gson.Gson;

/**
 * 集合对象的一些助手工具类
 * @qualiFild nc.pub.itf.tools.pub.CollectionUtil.java<br>
 * @author：licheng<br>
 * @date Created on 2019-8-12<br>
 * @version 1.0<br>
 */
public final class CollectionUtil implements PubConsUtilbc, Cloneable
{

    /**
     * 处理器接口，实现它即可
     * @qualiFild pers.bc.utils.pub.CollectionUtil.java<br>
     * @author：licheng<br>
     * @date Created on 2020年3月24日<br>
     * @version 1.0<br>
     */
    public interface MapHandler<T>
    {
        /**
         * 处理方法
         * @param map 具备指定特征的Map
         * @param key 特征键
         * @return 如果不想进行更改，直接return map即可，如果想修改，那么返回修改后的值即可
         */
        @SuppressWarnings("rawtypes")
        T handleObject(Map map, String key);
    }

    /**
     * *********************************************************** <br>
     * 执行处理方法 （因为传递的是内存地址，未做深拷贝，<br>
     * 所以无论是否接收返回值，原来的Map都会应用变化，<br>
     * 接收返回值 的目的只是为了当传入引用的tree发生改变时，<br>
     * 能够接收到处理后的新对象内存地址引用）
     * @param tree 原始Map
     * @param handleKey 特征键
     * @param handler 处理器
     * @return 返回处理结果
     * @methods pers.bc.utils.pub.CollectionUtil#action <br>
     * @author licheng <br>
     * @date Created on 2020年3月24日 <br>
     * @time 下午8:26:01 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static Object action(Map<Object, Object> tree, String handleKey, MapHandler handler)
    {
        Map<Object, Object> replaceTable = new LinkedHashMap<Object, Object>();
        if (tree.containsKey(handleKey))
        {
            return handler.handleObject(tree, handleKey);
        }
        for (Object key : tree.keySet())
        {
            if (!(key instanceof String)) continue;
            Object value = tree.get(key);
            if (value instanceof Map)
            {
                replaceTable.put(key, action((Map<Object, Object>) value, handleKey, handler));
            }
            else if (value instanceof List)
            {
                List<Object> valueList = (List<Object>) value;
                for (int i = 0; i < valueList.size(); i++)
                {
                    Object listItem = valueList.get(i);
                    if (listItem instanceof Map)
                    {
                        valueList.set(i, action((Map<Object, Object>) listItem, handleKey, handler));
                    }
                }
            }
        }
        for (Object key : replaceTable.keySet())
        {
            Object value = replaceTable.get(key);
            tree.put(key, value);
        }
        return tree;
    }

    /**
     * *********************************************************** <br>
     * 说明：对集合中元素进行特定的处理
     *
     * @param collection 集合
     * @param handler 实现特定处理的方法
     * @param <T> 泛型
     * @void <br>
     * @methods pers.bc.utils.pub.CollectionUtil#handler <br>
     * @author LiBencheng <br>
     * @date Created on 2020-6-30 <br>
     * @time 上午8:56:38 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static <T> void handler(Collection<T> collection, ObjectHandler<T> handler)
    {
        if (collection == null || collection.size() == 0)
        {
            return;
        }
        for (T t : collection)
        {
            handler.handler(t);
        }

    }

    /**
     * *********************************************************** <br>
     * 说明： 包含某一个元素
     * @see
     * @param element
     * @param t
     * @return
     * @boolean
     * @author licheng
     * @date Created on 2019-8-2
     * @time 上午10:21:38
     * @version 1.0 <br>
     * @param <T> <br>
     ***********************************************************/
    @SafeVarargs
    public static <T> boolean contains(Object element, T... t)
    {
        return Arrays.asList(t).contains(element);
    }

    /**
     * *********************************************************** <br>
     * 说明：对集合中的元素进行特定的处理，并获得处理后的结果
     * @see
     *
     * @param collection 待处理的集合
     * @param result 接受处理后结果的集合
     * @param process 实现的特定处理
     * @void
     * @author licheng
     * @date Created on 2019-8-2
     * @time 上午10:24:46
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static <T, E> void process(Collection<T> collection, Collection<E> result, ObjectProcess<T, E> process)
    {
        if (collection == null || collection.size() == 0)
        {
            return;
        }
        if (result == null || result.size() == 0)
        {
            return;
        }
        for (T t : collection)
        {
            E next = process.process(t);
            if (next != null)
            {
                result.add(next);
            }
        }
    }

    /**
     *
     * *********************************************************** <br>
     * 说明：去除重复元素
     * @see
     *
     * @param list 需要处理的list
     * @param <T> 泛型方法
     * @return
     * @List<T>
     * @author licheng
     * @date Created on 2019-8-2
     * @time 上午10:29:01
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static <T> List<T> removeDuplicate(List<T> list)
    {
        if (list == null || list.size() == 0)
        {
            return new ArrayList<T>();
        }
        return new ArrayList<T>(new HashSet<T>(list));

    }

    /**
     *
     * *********************************************************** <br>
     * 说明： 求俩个集合的交集
     * @see
     * @param list1
     * @param list2
     * @return
     * @List<T>
     * @author licheng
     * @date Created on 2019-8-2
     * @time 上午10:29:18
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static <T> List<T> intersection(List<T> list1, List<T> list2)
    {
        if (!PubEnvUtilbc.isEffective(list1, list2))
        {
            Set<T> set = new HashSet<T>(list1);
            set.retainAll(list2);
            return new ArrayList<T>(set);
        }
        return new ArrayList<T>();
    }

    /**
     * *********************************************************** <br>
     * 说明： 求俩个集合的交集
     *
     * @param set1 集合
     * @param set2 集合
     * @param <T> 泛型
     * @return 交集
     * @Set<T> <br>
     * @methods pers.bc.utils.pub.CollectionUtil#intersection <br>
     * @author LiBencheng <br>
     * @date Created on 2020-6-30 <br>
     * @time 上午8:57:19 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static <T> Set<T> intersection(Set<T> set1, Set<T> set2)
    {
        if (!PubEnvUtilbc.isEffective(set1, set2))
        {
            List<T> list = new ArrayList<T>(set1);
            list.retainAll(set2);
            return new HashSet<T>(list);
        }
        return new HashSet<T>();
    }

    /**
     * *********************************************************** <br>
     * 说明： 求队列的交集
     *
     * @param queue1 队列
     * @param queue2 队列
     * @param <T> 泛型
     * @return 交集
     * @Queue<T> <br>
     * @methods pers.bc.utils.pub.CollectionUtil#intersection <br>
     * @author LiBencheng <br>
     * @date Created on 2020-6-30 <br>
     * @time 上午8:57:38 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static <T> Queue<T> intersection(Queue<T> queue1, Queue<T> queue2)
    {
        if (!PubEnvUtilbc.isEffective(queue1, queue2))
        {
            Set<T> set = new HashSet<T>(queue1);
            set.retainAll(queue2);
            return new LinkedList<T>(set);
        }
        return new LinkedList<T>();
    }

    /**
     * *********************************************************** <br>
     * 说明： Map集合的交集只提供键的交集
     *
     * @param map1 map
     * @param map2 map
     * @param <K> 泛型
     * @param <V> 泛型
     * @return 交集
     * @Map<K,V> <br>
     * @methods pers.bc.utils.pub.CollectionUtil#intersection <br>
     * @author LiBencheng <br>
     * @date Created on 2020-6-30 <br>
     * @time 上午8:57:51 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static <K, V> Map<K, V> intersection(Map<K, V> map1, Map<K, V> map2)
    {
        Map<K, V> map = new HashMap<K, V>(map1.size());
        if (!PubEnvUtilbc.isEffective(map1, map2))
        {
            Set<K> setkey1 = new HashSet<K>(map1.keySet());
            Set<K> setkey2 = new HashSet<K>(map2.keySet());
            setkey1.retainAll(setkey2);
            for (K k : setkey1)
            {
                map.put(k, map1.get(k));
            }
        }
        return map;
    }

    /**
     * *********************************************************** <br>
     * 说明：求俩个集合的并集 <br>
     * @see <br>
     * @param list1
     * @param list2
     * @return <br>
     * @List<T> <br>
     * @methods pers.bc.utils.pub.CollectionUtil#unicon <br>
     * @author LiBencheng <br>
     * @date Created on 2020-6-30 <br>
     * @time 上午8:53:54 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static <T> List<T> unicon(List<T> list1, List<T> list2)
    {
        List<T> list = new ArrayList<T>();
        list.addAll(list1);
        list.addAll(list2);
        return list;
    }

    /**
     * *********************************************************** <br>
     * 说明：求俩个集合的交集 <br>
     * @see <br>
     * @param set1 set
     * @param set2 set
     * @param <T> 泛型
     * @return 交集
     * @Set<T> <br>
     * @methods pers.bc.utils.pub.CollectionUtil#unicon <br>
     * @author LiBencheng <br>
     * @date Created on 2020-6-30 <br>
     * @time 上午8:54:07 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static <T> Set<T> unicon(Set<T> set1, Set<T> set2)
    {
        set1.addAll(set2);
        return set1;
    }

    /**
     * *********************************************************** <br>
     * 说明： 求俩个集合的交集
     * @param queue1 队列
     * @param queue2 队列
     * @param <T> 泛型
     * @return 交集
     * @Queue<T> <br>
     * @methods pers.bc.utils.pub.CollectionUtil#unicon <br>
     * @author LiBencheng <br>
     * @date Created on 2020-6-30 <br>
     * @time 上午8:54:28 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static <T> Queue<T> unicon(Queue<T> queue1, Queue<T> queue2)
    {
        queue1.addAll(queue2);
        return queue1;
    }

    /**
     * *********************************************************** <br>
     * 说明：求俩个map的交集
     *
     * @param map1 map
     * @param map2 map
     * @param <K> 泛型
     * @param <V> 泛型
     * @return 交集
     * @Map<K,V> <br>
     * @methods pers.bc.utils.pub.CollectionUtil#unicon <br>
     * @author LiBencheng <br>
     * @date Created on 2020-6-30 <br>
     * @time 上午8:58:14 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static <K, V> Map<K, V> unicon(Map<K, V> map1, Map<K, V> map2)
    {
        Map<K, V> map = new HashMap<K, V>(map1.size() + map2.size());
        map.putAll(map1);
        map.putAll(map2);
        return map;
    }

    /**
     * *********************************************************** <br>
     * 说明： 求俩个集合的差集
     * @see
     * @param list1
     * @param list2
     * @return
     * @List<T>
     * @author licheng
     * @date Created on 2019-7-24
     * @time 下午7:33:20
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static <T> List<T> subtract(List<T> list1, List<T> list2)
    {
        List<T> list = new ArrayList<T>(list1.size() + list2.size());
        if (!PubEnvUtilbc.isEmptyColle(list1))
        {
            list.addAll(list1);
            list.removeAll(list2);
        }
        return list;
    }

    /**
     * *********************************************************** <br>
     * 说明： 求俩个集合的差集
     * @see
     * @param set1
     * @param set2
     * @return
     * @Set<T>
     * @author licheng
     * @date Created on 2019-7-24
     * @time 下午7:33:31
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static <T> Set<T> subtract(Set<T> set1, Set<T> set2)
    {
        Set<T> set = new HashSet<T>(set1.size() + set2.size());
        if (!PubEnvUtilbc.isEmptyColle(set1))
        {
            set.addAll(set1);
            set.removeAll(set2);
        }
        return set;
    }

    /**
     * *********************************************************** <br>
     * 说明： 求俩个集合的差集
     * @see
     * @param queue1 队列
     * @param queue2 队列
     * @return差集
     * @Queue<T> 泛型
     * @author licheng
     * @date Created on 2019-7-24
     * @time 下午7:33:47
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static <T> Queue<T> subtract(Queue<T> queue1, Queue<T> queue2)
    {
        Queue<T> queue = new LinkedList<T>();
        if (!PubEnvUtilbc.isEmptyColle(queue1))
        {
            queue.addAll(queue1);
            queue.removeAll(queue2);
        }
        return queue;
    }

    /**
     * *********************************************************** <br>
     * 说明： 求俩个集合的差集
     *
     * @param map1 map
     * @param map2 map
     * @param <K> 泛型
     * @param <V> 泛型
     * @return 差集
     * @Map<K,V> <br>
     * @methods pers.bc.utils.pub.CollectionUtil#subtract <br>
     * @author LiBencheng <br>
     * @date Created on 2020-6-30 <br>
     * @time 上午8:58:33 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static <K, V> Map<K, V> subtract(Map<K, V> map1, Map<K, V> map2)
    {
        Map<K, V> map = new HashMap<K, V>(map1.size() + map2.size());
        if (!PubEnvUtilbc.isEffective(map1, map2))
        {
            Set<K> setkey1 = new HashSet<K>(map1.keySet());
            Set<K> setkey2 = new HashSet<K>(map2.keySet());
            for (K k : setkey2)
            {
                setkey1.remove(k);
            }
            for (K k : setkey1)
            {
                map.put(k, map1.get(k));
            }
        }
        return map;

    }

    /**
     * *********************************************************** <br>
     * 说明：将List以separator链接并以字符串的形式返回
     *
     * @param collection collection
     * @param separator 连接符
     * @param <T> 泛型
     * @return 差集 <br>
     * @String <br>
     * @methods pers.bc.utils.pub.CollectionUtil#join <br>
     * @author LiBencheng <br>
     * @date Created on 2020-6-30 <br>
     * @time 上午8:58:46 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static <T> String join(Collection<T> collection, String separator)
    {
        StringBuilder sb = new StringBuilder();
        for (T t : collection)
        {
            sb.append(t.toString()).append(separator);
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }

    /**
     * *********************************************************** <br>
     * 说明： 将Map以separator链接并以字符串的形式返回<br>
     * @see <br>
     * @param map
     * @param separator
     * @param separator1
     * @return 字符串<br>
     * @String <br>
     * @methods pers.bc.utils.pub.CollectionUtil#join <br>
     * @author LiBencheng <br>
     * @date Created on 2020-6-30 <br>
     * @time 上午8:59:02 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static <K, V> String join(Map<K, V> map, String separator, String separator1)
    {
        if (map == null || map.size() == 0)
        {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<K, V> entry : map.entrySet())
        {
            sb.append(String.valueOf(entry.getKey())).append(separator1).append(String.valueOf(entry.getValue())).append(separator);
        }
        return sb.toString().substring(0, sb.toString().length() - separator.length());
    }

    /**
     * *********************************************************** <br>
     * 说明：将map的key以separator链接并以字符串的形式返回
     *
     * @param map map
     * @param separator 连接符
     * @param <K> 泛型
     * @param <V> 泛型
     * @return 字符串<br>
     * @String <br>
     * @methods pers.bc.utils.pub.CollectionUtil#keyJoin <br>
     * @author LiBencheng <br>
     * @date Created on 2020-6-30 <br>
     * @time 上午8:59:23 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static <K, V> String keyJoin(Map<K, V> map, String separator)
    {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<K, V> entry : map.entrySet())
        {
            sb.append(String.valueOf(entry.getKey())).append(separator);
        }
        return sb.toString().substring(0, sb.toString().length() - separator.length());
    }

    /**
     * *********************************************************** <br>
     * 说明： 将map的value以separator链接并以字符串的形式返回
     *
     * @param map map
     * @param separator 连接符
     * @param <K> 泛型
     * @param <V> 泛型
     * @return 字符串 <br>
     * @String <br>
     * @methods pers.bc.utils.pub.CollectionUtil#valueJoin <br>
     * @author LiBencheng <br>
     * @date Created on 2020-6-30 <br>
     * @time 上午8:59:36 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static <K, V> String valueJoin(Map<K, V> map, String separator)
    {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<K, V> entry : map.entrySet())
        {
            sb.append(String.valueOf(entry.getValue())).append(separator);
        }
        return sb.toString().substring(0, sb.toString().length() - separator.length());
    }

    /**
     * *********************************************************** <br>
     * 说明：解析Json到Map <br>
     * @see <br>
     * @param queryParam
     * @return <br>
     * @LinkedHashMap <br>
     * @methods pers.bc.utils.pub.CollectionUtil#jsonExchangeToMap <br>
     * @author licheng <br>
     * @date Created on 2020年3月24日 <br>
     * @time 下午8:09:24 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    @SuppressWarnings({"unchecked"})
    public static LinkedHashMap<Object, Object> jsonToMap(String queryParam)
    {
        LinkedHashMap<Object, Object> parameters = null;
        if (queryParam != null)
        {
            try
            {
                parameters = new Gson().fromJson(queryParam, LinkedHashMap.class);
            }
            catch (Exception e)
            {
                throw new RuntimeException("JSON格式错误");
            }
        }
        if (parameters == null) parameters = new LinkedHashMap<Object, Object>();
        return parameters;
    }

    /**
     * *********************************************************** <br>
     * 说明：XML格式字符串转换为Ma <br>
     * @see <br>
     * @param strXML XML字符串
     * @return XML数据转换后的Map
     * @throws Exception
     * @Map<String,String> <br>
     * @methods pers.bc.utils.pub.CollectionUtil#xmlToMap <br>
     * @author licheng <br>
     * @date Created on 2020年4月3日 <br>
     * @time 下午5:02:03 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static Map<String, String> xmlToMap(String strXML) throws Exception
    {
        try
        {
            Map<String, String> data = new HashMap<String, String>();
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            InputStream stream = new ByteArrayInputStream(strXML.getBytes(UTF_8));
            org.w3c.dom.Document doc = documentBuilder.parse(stream);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getDocumentElement().getChildNodes();
            for (int idx = 0; idx < nodeList.getLength(); ++idx)
            {
                Node node = nodeList.item(idx);
                if (node.getNodeType() == Node.ELEMENT_NODE)
                {
                    org.w3c.dom.Element element = (org.w3c.dom.Element) node;
                    data.put(element.getNodeName(), element.getTextContent());
                }
            }
            stream.close();

            return data;
        }
        catch (Exception ex)
        {
            LoggerUtilbc.getInstance("publogs").exception("xmlToMapThw", ex);
            throw ex;
        }

    }

    /**
     * *********************************************************** <br>
     * 说明：将Map转换为XML格式的字符串 <br>
     * @see <br>
     * @param data Map类型数据
     * @return XML格式的字符串
     * @throws Exception <br>
     * @String <br>
     * @methods pers.bc.utils.pub.CollectionUtil#mapToXml <br>
     * @author licheng <br>
     * @date Created on 2020年4月3日 <br>
     * @time 下午4:58:23 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static String mapToXml(Map<String, String> data) throws Exception
    {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        org.w3c.dom.Document document = documentBuilder.newDocument();
        org.w3c.dom.Element root = document.createElement("xml");
        document.appendChild(root);
        for (String key : data.keySet())
        {
            String value = data.get(key);
            if (value == null)
            {
                value = "";
            }
            value = value.trim();
            org.w3c.dom.Element filed = document.createElement(key);
            filed.appendChild(document.createTextNode(value));
            root.appendChild(filed);
        }
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        DOMSource source = new DOMSource(document);
        transformer.setOutputProperty(OutputKeys.ENCODING, UTF_8);
        transformer.setOutputProperty(OutputKeys.INDENT, YES);
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        transformer.transform(source, result);
        String output = writer.getBuffer().toString(); // .replaceAll("\n|\r", "");
        try
        {
            writer.close();
        }
        catch (Exception ex)
        {
            LoggerUtilbc.getInstance("publogs").exception("mapToXmlThw", ex);
        }
        return output;
    }

    /**
     * *********************************************************** <br>
     * 说明：mapList转json <br>
     * @see <br>
     * @param maps
     * @return <br>
     * @String <br>
     * @methods pers.bc.utils.pub.CollectionUtil#map2JosnStr <br>
     * @author LiBencheng <br>
     * @date Created on 2020-6-30 <br>
     * @time 上午9:00:06 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static String map2JosnStr(List<Map<?, ?>> maps)
    {
        if (maps == null || maps.size() < 1) return PubConsUtilbc.EMPTY;

        return new Gson().toJson(maps, List.class);
    }

    /**
     * *********************************************************** <br>
     * 说明： map转json<br>
     * @see <br>
     * @param map
     * @return <br>
     * @String <br>
     * @methods pers.bc.utils.pub.CollectionUtil#map2JosnStr <br>
     * @author LiBencheng <br>
     * @date Created on 2020-6-30 <br>
     * @time 上午8:59:56 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static String map2JosnStr(Map<?, ?> map)
    {
        return new Gson().toJson(map, Map.class);
    }

    /**
     * *********************************************************** <br>
     * 说明： map转json,内部类，建议使用这个方法<br>
     * @see <br>
     * @param map
     * @return <br>
     * @String <br>
     * @methods pers.bc.utils.pub.CollectionUtil#map2JosnStr <br>
     * @author LiBencheng <br>
     * @date Created on 2020-6-30 <br>
     * @time 上午8:59:56 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     * @throws Exception
     */
    public static String map2Josn(Map<?, ?> map) throws Exception
    {

        if (PubEnvUtilbc.getSize(map) < 1) return EMPTY;
        StringBuilder sb = new StringBuilder(LEFT_BRACE);
        for (Object key : map.keySet())
        {
            Object obj = map.get(key);
            if (PubEnvUtilbc.isEmptyObj(obj)) sb.append(QUOTE).append(key).append(QUOTE).append(COLON).append(QUOTE).append(obj)
                .append(QUOTE).append(COMMA).append(CRLF);
            else
            {
                if (obj instanceof Map) sb.append(QUOTE).append(key).append(QUOTE).append(COLON).append(map2Josn((Map<?, ?>) obj))
                    .append(COMMA).append(CRLF);
                else if (obj instanceof List) sb.append(QUOTE).append(key).append(QUOTE).append(COLON).append(map2Josn((List<?>) obj))
                    .append(COMMA).append(CRLF);
                else if (BeanHelper.isBeanClass(obj.getClass())) sb.append(QUOTE).append(key).append(QUOTE).append(COLON)
                    .append(bean2Josn(obj)).append(COMMA).append(CRLF);
                else if (StringUtilbc.valueOfNull(obj).contains(LEFT_BRACE) || StringUtilbc.valueOfNull(obj).contains(LEFT_SQ_BRACKET))
                {
                    sb.append(QUOTE).append(key).append(QUOTE).append(COLON).append(obj).append(COMMA).append(CRLF);
                }
                else
                    sb.append(QUOTE).append(key).append(QUOTE).append(COLON).append(QUOTE).append(obj).append(QUOTE).append(COMMA)
                        .append(CRLF);
            }
        }

        return deleteStr(sb, 3).append(RIGHT_BRACE).toString();
    }

    /**
     * *********************************************************** <br>
     * 说明：bean2Josn <br>
     * @see <br>
     * @param obj
     * @return
     * @throws Exception <br>
     * @String <br>
     * @methods pers.bc.utils.pub.CollectionUtil#bean2Josn <br>
     * @author LiBencheng <br>
     * @date Created on 2020-12-30 <br>
     * @time 下午3:57:56 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static String bean2Josn(Object obj) throws Exception
    {
        if (PubEnvUtilbc.isEmptyObj(obj)) return EMPTY;
        if (BeanHelper.isBeanClass(obj.getClass()))
        {
            Map<String, Object> beanMap = BeanHelper.getBeanInfo(obj);
            beanMap.putAll(BeanHelper.transBean2Map(obj));
            return map2Josn(beanMap);
        }

        return obj.toString();
    }

    /**
     * *********************************************************** <br>
     * 说明：mapList转json 内部类，建议使用这个方法<br>
     * @see <br>
     * @param mapList
     * @return <br>
     * @String <br>
     * @throws Exception
     * @methods pers.bc.utils.pub.CollectionUtil#map2JosnStr <br>
     * @author LiBencheng <br>
     * @date Created on 2020-6-30 <br>
     * @time 上午9:00:06 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static String map2Josn(List<?> mapList) throws Exception
    {
        if (PubEnvUtilbc.getSize(mapList) < 1) return EMPTY;
        StringBuilder strb = new StringBuilder(LEFT_SQ_BRACKET);
        for (Object obj : mapList)
        {
            if (PubEnvUtilbc.isEmptyObj(obj)) strb.append(obj).append(COMMA).append(CRLF);
            else
            {
                if (obj instanceof Map) strb.append(map2Josn((Map<?, ?>) obj)).append(COMMA).append(CRLF);
                else if (BeanHelper.isBeanClass(obj.getClass())) strb.append(bean2Josn(obj)).append(COMMA).append(CRLF);
                else
                    strb.append(obj).append(COMMA).append(CRLF);
            }
        }

        return deleteStr(strb, 3).append(RIGHT_SQ_BRACKET).toString();
    }

    /**
     * *********************************************************** <br>
     * 说明： <br>
     * @see <br>
     * @param sbStr
     * @param i
     * @return <br>
     * @StringBuilder <br>
     * @methods pers.bc.utils.pub.CollectionUtil#deleteStr <br>
     * @author LiBencheng <br>
     * @date Created on 2020-10-17 <br>
     * @time 上午11:03:45 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static StringBuilder deleteStr(StringBuilder sbStr, int i)
    {
        return sbStr.delete(sbStr.length() - i, sbStr.length());
    }

    /**
     * *********************************************************** <br>
     * 说明： <br>
     * @see <br>
     * @param obj
     * @return
     * @throws Exception <br>
     * @Object <br>
     * @methods pers.bc.utils.pub.CollectionUtil#Cloneable <br>
     * @author LiBencheng <br>
     * @date Created on 2020-12-29 <br>
     * @time 11:10:52 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    @SuppressWarnings("unchecked")
    public static Object cloneable(Object obj) throws Exception
    {
        Object newObj = null;
        if (PubEnvUtilbc.isEmptyObj(obj)) return newObj;
        if (!obj.getClass().getName().contains(PubConsUtilbc.DOLLAR))
        {
            if (obj instanceof Map)
            {
                Map newMap = (Map) obj;
                newObj = ReflectionUtilbc.getNewInstance(obj.getClass());
                Set keySet = newMap.keySet();
                ((Map) newObj).putAll(newMap);
            }
            else if (obj instanceof Collection)
            {
                if (obj instanceof List)
                {
                    List oldList = (List) obj;
                    newObj = ReflectionUtilbc.getNewInstance(obj.getClass());
                    ((List) newObj).addAll(oldList);
                }
                if (obj instanceof Set)
                {
                    Set oldSet = (Set) obj;
                    newObj = ReflectionUtilbc.getNewInstance(obj.getClass());
                    ((Set) newObj).addAll(oldSet);
                }
            }
            else if (obj.getClass().isArray())
            {
                List<Object> asList = Arrays.asList(obj);
                Object[] strs = new Object[asList.size()];
                System.arraycopy(asList.toArray(), 0, strs, 0, asList.size());
                newObj = Arrays.asList(strs).toArray();
            }
            else
                newObj = ReflectionUtilbc.newInstance(obj.getClass(), obj);
        }
        else
        {
            if (obj instanceof Map)
            {
                Map newMap = (Map) obj;
                newObj = new LinkedHashMap();
                Set keySet = newMap.keySet();
                for (Object key : keySet)
                {
                    ((Map) newObj).put(key, newMap.get(key));
                }
            }
            else if (obj instanceof Collection)
            {

                if (obj instanceof List)
                {
                    List oldList = (List) obj;
                    newObj = new LinkedList();
                    java.util.Collections.copy(((LinkedList) newObj), oldList);
                }
                if (obj instanceof Set)
                {
                    Set oldSet = (Set) obj;
                    newObj = new LinkedHashSet();
                    ((Set) newObj).addAll(oldSet);
                }
            }
            else if (obj.getClass().isArray())
            {
                List<Object> asList = Arrays.asList(obj);
                Object[] strs = new Object[asList.size()];
                System.arraycopy(asList.toArray(), 0, strs, 0, asList.size());
                newObj = Arrays.asList(strs).toArray();
            }
            else
                newObj = obj;
        }

        return newObj;
    }

    /**
     * *********************************************************** <br>
     * 说明： 对象深度克隆---使用序列化进行深拷贝<br>
     * 注意： 使用序列化的方式来实现对象的深拷贝，<br>
     * 但是前提是，对象必须是实现了 Serializable接口才可以，Map本身没有实现 Serializable 这个接口，<br>
     * 所以这种方式不能序列化Map，也就是不能深拷贝Map。<br>
     * 但是HashMap是可以的，因为它实现了Serializable。
     * @see <br>
     * @param <T>
     * @param obj
     * @return <br>
     * @T <br>
     * @throws IOException
     * @throws ClassNotFoundException <br>
     * @methods pers.bc.utils.pub.CollectionUtil#clone <br>
     * @author LiBencheng <br>
     * @date Created on 2020-12-29 <br>
     * @time 17:44:56 <br>
     * @version 1.0 <br>
     */
    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T cloneObj(Object obj) throws IOException, ClassNotFoundException
    {
        T clonedObj = null;
        try
        {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            oos.close();
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            clonedObj = (T) ois.readObject();
            ois.close();
        }
        catch (Exception e)
        {
            LoggerUtilbc.getInstance("publogs").exception("对象深度克隆---使用序列化进行深拷贝异常", e);
            throw new IOException("对象深度克隆---使用序列化进行深拷贝异常", e);
        }

        return clonedObj;
    }

}
