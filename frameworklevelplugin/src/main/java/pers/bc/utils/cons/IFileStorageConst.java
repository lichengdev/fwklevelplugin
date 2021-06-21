package pers.bc.utils.cons;

/**
 * 常量定义
 * @qualiFild com.pub.utils.cons.IFileStorageConst.java<br>
 * @author：licheng<br>
 * @date Created on 2019-8-12<br>
 * @version 1.0<br>
 */
public interface IFileStorageConst {
    /** 文件头表名 **/
    String HEADTABLENAME = "bap_fs_header";

    /** 文件体表名 **/
    String BODYTABLNAME = "bap_fs_body";

    /**
     * 默认存储database name
     */
    String DEFAULTDB = "UFS";

    /** URL参数的编码格式 */
    String URL_ENCODE = "utf-8";

    /** 表字段分隔符 */
    String DB_SEPERATOR = "@@@";

    /** 无效的文件大小 **/
    long INVALID_SIZE = -1L;

    /** 无效的文件名 **/
    String INVALID_NAME = "invalid.invalid";

    String VERSIONNO = "versionno";

    /** --------------------扩展属性标识为了跟协同的匹配--------------------- **/

    String PAR_BILLITEM = "billitem";

    String PAR_BILLTYPE = "billtype";

    String PAR_FILEPK = "filepk";

    String PAR_ISCOVER = "iscover";

    String PAR_ISBILL = "isbill";

    String PARA_MODULE = "bamodule";

    /**
     * 是否在线显示
     */
    String PAR_ISVIEW = "isView";

    /**
     * 默认Bucket
     */
    String DEFAULT_BUCKET = "default";
    
    /**
     * 文档中心默认Bucket
     */
    String DEFAULT_DOCUMENT_BUCKET = "documentroot";

    /**
     * 压缩标记
     */
    String FLAG_ZIP = ".zip";

    /**
     * 是否覆盖写
     */
    String ATTRIBUTE_OVERRIDE = "override";

    String EXT_CONNECTOR = "_";

    /** ----------------servlet config------------------ */

    String TRANSFER_HTTP = "http";

    String TRANSFER_HTTPS = "https";

    String SERVLET_SERVICE = "fs/service";

    String SERVLET_PART_FILES = "files";

    String SERVLET_URL_FILES = "ufiles";

    /** ---------------------文件服务所需的属性集合--------------------------- */
    /**
     * 模块下的文件存储路径
     */
    String ATTRIBUTE_FILEPATH = "filePath";

    /**
     * 文件名(上传文件时需要)
     */
    String ATTRIBUTE_FILENAME = "fileName";

    /**
     * 新的文件名(重命名文件与复制文件时需要)
     */
    String ATTRIBUTE_NEW_FILENAME = "newfileName";

    /**
     * 模块编码
     */
    String ATTRIBUTE_QUERY_PARAMS = "queryParams";

    /**
     * 数据库分页大小
     */
    String ATTRIBUTE_PAGESIZE = "pageSize";

    /**
     * 分页的页数
     */
    String ATTRIBUTE_PAGEINDEX = "pageIndex";

    /**
     * 表bap_fs_header中的列
     */
    String ATTRIBUTE_COLUMNS = "columnInHeader";

    /**
     * 升序降序
     */
    String ATTRIBUTE_DESC = "desc";

    /**
     * 跨模块的编码（用于跨模块复制）
     */
    String ATTRIBUTE_CROSSBUCKET = "crossBucket";

    /**
     * 操作用户标识
     */
    String ATTRIBUTE_CURR_USER = "curruser";

    String ATTRIBUTE_EXTPROPS = "extProps";

    String ATTRIBUTE_HEADER = "fileheader";

    String ATTRIBUTE_BODY = "filebody";

    /**
     * 开始读取文件的位置
     */
    String ATTRIBUTE_BEGINPOS = "beginPos";

    /**
     * 停止读取文件的位置
     */
    String ATTRIBUTE_ENDPOS = "endPos";

    String ATTRIBUTE_FILESIZE = "fileSize";

    String ATTRIBUTE_STOREPATH = "storePath";

    String ATTRIBUTE_EXIST_HEADER = "existHeader";

    /**
     * 文件的操作标识
     */
    String ATTRIBUTE_OPERATED = "operated";

    /**
     * 缩略图尺寸(宽度和高度)
     */
    String THUMBNIAL_WIDTH = "width";
    String THUMBNIAL_HEIGHT = "height";

    /** ---------------文件服务各种操作集合------------------------ **/
    /**
     * 上传文件
     */
    int UPLOAD_FILE = 0;

    /**
     * 更新扩展属性
     */
    int UPDATE_EXTPOPRS = 1;

    /**
     * 更新文件
     */
    int UPDATE_FILE = 2;

    /**
     * 查询文件头（以文件逻辑路径）
     */
    int QUERY_HEADER_PATH = 3;

    /**
     * 查询文件头（以扩展属性）
     */
    int QUERY_HEADER_EXTPROPS = 4;

    /**
     * 查询扩展属性(以文件头信息)
     */
    int QUERY_EXTPROP_HEADER = 5;
    /**
     * 查询扩展属性（以文件逻辑路径）
     */
    int QUERY_EXTPROP_PATH = 6;

    /**
     * 复制--不指定存储位置
     */
    int CROSS_COPY = 7;

    /**
     * 复制--指定存储位置（FTP使用）
     */
    int COPY_WITH_PATH = 8;

    /**
     * 查询文件头信息组合模型（以文件逻辑路径）
     */
    int QUERY_HEADMODEL_PATH = 9;

    /**
     * 查询文件头信息组合模型（以扩展属性）
     */
    int QUERY_HEADMODEL_EXTPROPS = 10;

    /**
     * 查询文件是否存在
     */
    int QUERY_FILE_EXIST = 11;

    /***
     * 更新操作（包括更新文件、元数据信息）
     */
    int UPDATE = 12;

    /**
     * 查询文件的存储信息
     */

    int QUERY_STOREINFO = 13;

    int UPLOAD_META = 14;

    int QUERY_HEADER_ATTRS = 15;

    int QUERY_HEAD_BODY = 16;

    /**
     * 同一模块复制
     */
    int COPY_IN_MODULE = 17;

    int QUERY_RELATED_INFO = 18;

    int UPDATE_META = 19;

    int UPLOAD_VERSION_FILE = 20;

    int UPLOAD_VERSION_META = 21;

    int QUERY_VERSION = 22;

    /**
     * 缩略图操作
     */
    int FETCH_THUMBNAIL = 23;

    /** --------------------文件存储目录生成规则-------------------------- **/
    String STOREBYDATE = "bydate";
    String STOREBYMODULE = "bymodule";

    /** --------------------文件服务器存储类型-------------------------- **/
    String DEFAULT_STORETYPE = "default";
    String DISK_STORETYPE = "disk";
    String FTP_STORETYPE = "ftp";

    int DEFAULT_STORETYPE_INT = 0;
    int DISK_STORETYPE_INT = 1;
    int FTP_STORETYPE_INT = 2;

    /** --------------------启动Mongo服务参数-------------------------- **/
    String MONGOPORT = "mongoPort";
    String MONGODIRECTORY = "mongoDirectory";
    int MONGOSERVICE = 25;
    
    
    /**
     * 文件服务器当前版本2.0
     */
    String CURRENT_VERSION = "V2.0";
}
