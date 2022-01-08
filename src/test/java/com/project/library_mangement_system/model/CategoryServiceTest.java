package com.project.library_mangement_system.model;

import com.project.library_mangement_system.repository.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private CategoryService categoryService;

    private Category category;

    private List<Category> categoryList;

    @Before
    public void setUp() {
        this.categoryService=new CategoryService(this.categoryRepository);
        category= new Category("Children Story","Child");
        category=categoryService.save(category);
    }

    @Test
    public void testGetTotalCount() {
        Long i=categoryService.getTotalCount();
        verify(categoryRepository).count();
        assertNotNull(i);
    }

    @Test
    public void testGetAllBySort() {
        categoryList=categoryService.getAllBySort();
        verify(categoryRepository).findAllByOrderByNameAsc();
        assertNotNull(categoryList);
    }

    @Test
    public void testGetAll() {
        categoryList=categoryService.getAll();
        verify(categoryRepository).findAll();
        assertNotNull(categoryList);
    }

    @Test
    public void testDelete() {
        categoryService.delete(category);
        verify(categoryRepository).delete(category);
        assertNull(category);
    }

    @Test
    public void testTestDelete() {
        categoryService.delete(1L);
        verify(categoryRepository).deleteById(1L);
        assertNull(category);
    }

    @Test
    public void testAddNew() {
        Category category=new Category("Ghost Story","ghot");
        categoryService.addNew(category);
        verify(categoryRepository).save(category);
        assertNotNull(category.getName());
    }

    @Test
    public void testSave() {
        Category category=new Category("Ghost Story","ghot");
        categoryService.save(category);
        verify(categoryRepository).save(category);
        assertNotNull(category);
    }
}