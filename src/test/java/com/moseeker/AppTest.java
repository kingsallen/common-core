package com.moseeker;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;

public class AppTest {
	
	public static void main(String[] args) throws Exception{

	}

	@Test
	public void test00(){
		Assert.assertEquals(true,Integer.valueOf("2131").equals(Integer.valueOf("2131")));
	}
}
