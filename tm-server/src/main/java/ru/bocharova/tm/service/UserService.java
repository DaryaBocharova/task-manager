package ru.bocharova.tm.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.AllArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.eclipse.persistence.jaxb.JAXBContextFactory;
import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import ru.bocharova.tm.api.repository.ITaskRepository;
import ru.bocharova.tm.api.service.IUserService;
import ru.bocharova.tm.exception.AuthenticationSecurityException;
import ru.bocharova.tm.api.repository.IProjectRepository;
import ru.bocharova.tm.api.repository.IUserRepository;
import ru.bocharova.tm.entity.Project;
import ru.bocharova.tm.entity.Task;
import ru.bocharova.tm.entity.User;
import ru.bocharova.tm.util.Domain;
import ru.bocharova.tm.util.HashUtil;
import ru.bocharova.tm.util.EnumUtil;
import ru.bocharova.tm.util.StringValidator;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public final class UserService implements IUserService {

    @NotNull
    private final SqlSessionFactory sessionFactory;

    @Override
    public User create(@NotNull final String login, @NotNull final String password, @NotNull final String role) {
        if (!StringValidator.validate(login, password, role)) return null;
        if (EnumUtil.stringToRole(role) == null) return null;
        @NotNull final User user = new User(login, HashUtil.md5(password), EnumUtil.stringToRole(role));
        @NotNull SqlSession session = null;
        try {
            session = sessionFactory.openSession();
            session.getMapper(IUserRepository.class).persist(user);
            session.commit();
            return user;
        } catch (Exception e) {
            if (session != null) session.rollback();
        } finally {
            if (session != null) session.close();
        }
        return null;
    }

    public User create(@NotNull final String id, @NotNull final String login, @NotNull final String password, @NotNull final String role) {
        if (!StringValidator.validate(id, login, password, role)) return null;
        if (EnumUtil.stringToRole(role) == null) return null;
        @NotNull final User user = new User(login, HashUtil.md5(password),  EnumUtil.stringToRole(role));
        user.setId(id);
        @NotNull SqlSession session = null;
        try {
            session = sessionFactory.openSession();
            session.getMapper(IUserRepository.class).persist(user);
            session.commit();
            return user;
        } catch (Exception e) {
            if (session != null) session.rollback();
        } finally {
            if (session != null) session.close();
        }
        return null;
    }

    @Override
    public User edit(@NotNull final String id, @NotNull final String login, @NotNull final String password, @NotNull final String role) {
        if (!StringValidator.validate(id, login, password, role)) return null;
        if (EnumUtil.stringToRole(role) == null) return null;
        @NotNull User user = findOne(id);
        if (user == null) return null;
        user.setLogin(login);
        user.setPassword(HashUtil.md5(password));
        user.setRole(EnumUtil.stringToRole(role));
        @NotNull SqlSession session = null;
        try {
            session = sessionFactory.openSession();
            session.getMapper(IUserRepository.class).merge(user);
            session.commit();
            return user;
        } catch (Exception e) {
            if (session != null) session.rollback();
        } finally {
            if (session != null) session.close();
        }
        return null;
    }

    @Override
    public User edit(@NotNull final String id, @NotNull final String login, @NotNull final String password) {
        if (!StringValidator.validate(id, login, password)) return null;
        @Nullable User user = findOne(id);
        if (user == null) return null;
        user.setLogin(login);
        user.setPassword(HashUtil.md5(password));

        @NotNull SqlSession session = null;
        try {
            session = sessionFactory.openSession();
            session.getMapper(IUserRepository.class).merge(user);
            session.commit();
            return user;
        } catch (Exception e) {
            if (session != null) session.rollback();
        } finally {
            if (session != null) session.close();
        }
        return null;
    }

    @Override
    public User findByLogin(@NotNull final String login) {
        if (!StringValidator.validate(login)) return null;
        try (SqlSession session = sessionFactory.openSession()) {
            return session.getMapper(IUserRepository.class).findByLogin(login);
        }
    }

    @Override
    public void clear() {
        @NotNull SqlSession session = null;
        try {
            session = sessionFactory.openSession();
            session.getMapper(IUserRepository.class).removeAll();
            session.commit();
        } catch (Exception e) {
            if (session != null) session.rollback();
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public User findOne(@NotNull String id) {
        if (!StringValidator.validate(id)) return null;
        try (SqlSession session = sessionFactory.openSession()) {
            return session.getMapper(IUserRepository.class).findOne(id);
        }
    }

    @Override
    public User remove(@NotNull String id) {
        if (!StringValidator.validate(id)) return null;
        @Nullable final User user = findOne(id);
        if (user == null) return null;
        @NotNull SqlSession session = null;
        try {
            session = sessionFactory.openSession();
            session.getMapper(IUserRepository.class).remove(id);
            session.commit();
            return user;
        } catch (Exception e) {
            if (session != null) session.rollback();
        } finally {
            if (session != null) session.close();
        }
        return null;
    }

    @Override
    public Collection<User> findAll() {
        try (SqlSession session = sessionFactory.openSession()) {
            return session.getMapper(IUserRepository.class).findAll();
        }
    }

    @Override
    public User authenticationUser(@NotNull final String login, @NotNull final String password) throws AuthenticationSecurityException {
        if (!StringValidator.validate(login, password))
            throw new AuthenticationSecurityException("User login or password must not be empty!");
        @Nullable
        User user = findByLogin(login);
        if (user == null || !HashUtil.md5(password).equals(user.getPassword()))
            throw new AuthenticationSecurityException("Wrong login or password!");
        return user;
    }

    @Override
    public void loadData() {
        @Nullable Domain domain = null;
        try (ObjectInputStream oin = new ObjectInputStream(new FileInputStream("tm-server/data.out"))) {
            domain = (Domain) oin.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }
        loadAllDataFromDomain(domain);
    }

    @Override
    public void saveData() {
        @NotNull final Domain domain = saveAllDataToDomain();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("tm-server/data.out"))) {
            oos.writeObject(domain);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void loadDataJaxbXml() {
        @Nullable Domain domain = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Domain.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            domain = (Domain) unmarshaller.unmarshal(new File("tm-server/data.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
            return;
        }
        loadAllDataFromDomain(domain);
    }

    @Override
    public void saveDataJaxbXml() {
        @NotNull final Domain domain = saveAllDataToDomain();
        try (FileWriter fw = new FileWriter("tm-server/data.xml")) {
            fw.write(domainToXMLString(domain));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String domainToXMLString(@NotNull final Domain domain) {
        try {
            JAXBContext context = JAXBContext.newInstance(Domain.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
            StringWriter sw = new StringWriter();

            marshaller.marshal(domain, sw);
            return xmlToPretty(sw.toString(), 2);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String xmlToPretty(@NotNull final String xml, @NotNull final int indent) {
        try {
            // Turn xml string into a document
            Document document = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder()
                    .parse(new InputSource(new ByteArrayInputStream(xml.getBytes("utf-8"))));

            // Remove whitespaces outside tags
            document.normalize();
            XPath xPath = XPathFactory.newInstance().newXPath();
            NodeList nodeList = (NodeList) xPath.evaluate("//text()[normalize-space()='']",
                    document,
                    XPathConstants.NODESET);

            for (int i = 0; i < nodeList.getLength(); ++i) {
                Node node = nodeList.item(i);
                node.getParentNode().removeChild(node);
            }

            // Setup pretty print options
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformerFactory.setAttribute("indent-number", indent);
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            // Return pretty print xml string
            StringWriter stringWriter = new StringWriter();
            transformer.transform(new DOMSource(document), new StreamResult(stringWriter));
            return stringWriter.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void loadDataJaxbJSON() {
        @Nullable Domain domain = null;
        try {
            Map<String, Object> properties = new HashMap<String, Object>(3);
            properties.put(MarshallerProperties.MEDIA_TYPE, "application/json");
            properties.put(MarshallerProperties.JSON_INCLUDE_ROOT, Boolean.FALSE);
            properties.put(MarshallerProperties.JSON_WRAPPER_AS_ARRAY_NAME, Boolean.TRUE);
            JAXBContext context = JAXBContextFactory.createContext(new Class[]{Domain.class}, properties);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            StreamSource json = new StreamSource("tm-server/data.json");
            domain = unmarshaller.unmarshal(json, Domain.class).getValue();
        } catch (JAXBException e) {
            e.printStackTrace();
            return;
        }
        loadAllDataFromDomain(domain);
    }

    @Override
    public void saveDataJaxbJSON() {
        @NotNull final Domain domain = saveAllDataToDomain();
        try (FileWriter fw = new FileWriter("tm-server/data.json")) {
            fw.write(domainToJsonString(domain));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String domainToJsonString(@NotNull final Domain domain) {
        try {
            Map<String, Object> properties = new HashMap<String, Object>(3);
            properties.put(MarshallerProperties.MEDIA_TYPE, "application/json");
            properties.put(MarshallerProperties.JSON_INCLUDE_ROOT, Boolean.FALSE);
            properties.put(MarshallerProperties.JSON_WRAPPER_AS_ARRAY_NAME, Boolean.TRUE);
            JAXBContext context = JAXBContextFactory.createContext(new Class[]{Domain.class}, properties);
            Marshaller marshaller = context.createMarshaller();
            StringWriter sw = new StringWriter();
            marshaller.marshal(domain, sw);
            return jsonToPretty(sw.toString(), 2);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String jsonToPretty(@NotNull final String jsonString, @NotNull final int indent) {
        try {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine scriptEngine = manager.getEngineByName("JavaScript");
            scriptEngine.put("jsonString", jsonString);
            scriptEngine.eval("result = JSON.stringify(JSON.parse(jsonString), null, " + indent + ")");
            return (String) scriptEngine.get("result");
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void loadDataFasterXml() {
        @Nullable Domain domain = null;
        XmlMapper xmlMapper = new XmlMapper();
        try {
            domain = xmlMapper.readValue(new File("tm-server/data.xml"), Domain.class);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        loadAllDataFromDomain(domain);
    }

    @Override
    public void saveDataFasterXml() {
        @NotNull final Domain domain = saveAllDataToDomain();
        XmlMapper mapper = new XmlMapper();
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File("tm-server/data.xml"), domain);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadDataFasterJSON() {
        @Nullable Domain domain = null;
        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE);
        try {
            domain = mapper.readValue(new File("tm-server/data.json"), Domain.class);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        loadAllDataFromDomain(domain);
    }

    @Override
    public void saveDataFasterJSON() {
        @NotNull final Domain domain = saveAllDataToDomain();
        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE);
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File("tm-server/data.json"), domain);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadAllDataFromDomain(@NotNull final Domain domain) {
        @Nullable SqlSession session = null;
        try {
            session = sessionFactory.openSession();
            session.getMapper(ITaskRepository.class).removeAll();
            session.getMapper(IProjectRepository.class).removeAll();
            session.commit();
            for (Project project : domain.getProjects()) {
                session.getMapper(IProjectRepository.class).persist(project);
            }
            for (Task task : domain.getTasks()) {
                session.getMapper(ITaskRepository.class).persist(task);
            }
            session.commit();
        } catch (Exception e) {
            if (session != null) session.rollback();
        } finally {
            if (session != null) session.close();
        }
    }

    public Domain saveAllDataToDomain() {
        @NotNull final Domain domain;
        try (SqlSession session = sessionFactory.openSession()) {
            @NotNull final Collection<Project> projects = session.getMapper(IProjectRepository.class).findAll();
            @NotNull final Collection<Task> tasks = session.getMapper(ITaskRepository.class).findAll();
            domain = new Domain(new ArrayList<>(projects), new ArrayList<>(tasks));
        }
        return domain;
    }
}